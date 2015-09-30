/**
 * Generated by Scrooge
 *   version: 4.1.0
 *   rev: 87b84f89477a4737c8d57580a1e8bdaeac529b19
 *   built at: 20150928-114808
 */
package com.twitter.zipkin.thriftscala

import com.twitter.finagle.Thrift
import com.twitter.finagle.stats.{NullStatsReceiver, StatsReceiver}
import com.twitter.scrooge.{ThriftStruct, TReusableMemoryTransport}
import com.twitter.util.Future
import java.nio.ByteBuffer
import java.util.Arrays
import org.apache.thrift.protocol._
import org.apache.thrift.TApplicationException
import org.apache.thrift.transport.TMemoryInputTransport
import scala.collection.mutable.{
  ArrayBuffer => mutable$ArrayBuffer, HashMap => mutable$HashMap}
import scala.collection.{Map, Set}

import scala.language.higherKinds


@javax.annotation.Generated(value = Array("com.twitter.scrooge.Compiler"))
class DependencyStore$FinagleService(
  iface: DependencyStore[Future],
  protocolFactory: TProtocolFactory,
  stats: StatsReceiver,
  maxThriftBufferSize: Int
) extends com.twitter.finagle.Service[Array[Byte], Array[Byte]] {
  import DependencyStore._

  def this(
    iface: DependencyStore[Future],
    protocolFactory: TProtocolFactory
  ) = this(iface, protocolFactory, NullStatsReceiver, Thrift.maxThriftBufferSize)


  private[this] val tlReusableBuffer = new ThreadLocal[TReusableMemoryTransport] {
    override def initialValue() = TReusableMemoryTransport(512)
  }

  private[this] def reusableBuffer: TReusableMemoryTransport = {
    val buf = tlReusableBuffer.get()
    buf.reset()
    buf
  }

  private[this] val resetCounter = stats.scope("buffer").counter("resetCount")

  private[this] def resetBuffer(trans: TReusableMemoryTransport): Unit = {
    if (trans.currentCapacity > maxThriftBufferSize) {
      resetCounter.incr()
      tlReusableBuffer.remove()
    }
  }

  protected val functionMap = new mutable$HashMap[String, (TProtocol, Int) => Future[Array[Byte]]]()

  protected def addFunction(name: String, f: (TProtocol, Int) => Future[Array[Byte]]) {
    functionMap(name) = f
  }

  protected def exception(name: String, seqid: Int, code: Int, message: String): Future[Array[Byte]] = {
    try {
      val x = new TApplicationException(code, message)
      val memoryBuffer = reusableBuffer
      try {
        val oprot = protocolFactory.getProtocol(memoryBuffer)

        oprot.writeMessageBegin(new TMessage(name, TMessageType.EXCEPTION, seqid))
        x.write(oprot)
        oprot.writeMessageEnd()
        oprot.getTransport().flush()
        Future.value(Arrays.copyOfRange(memoryBuffer.getArray(), 0, memoryBuffer.length()))
      } finally {
        resetBuffer(memoryBuffer)
      }
    } catch {
      case e: Exception => Future.exception(e)
    }
  }

  protected def reply(name: String, seqid: Int, result: ThriftStruct): Future[Array[Byte]] = {
    try {
      val memoryBuffer = reusableBuffer
      try {
        val oprot = protocolFactory.getProtocol(memoryBuffer)

        oprot.writeMessageBegin(new TMessage(name, TMessageType.REPLY, seqid))
        result.write(oprot)
        oprot.writeMessageEnd()

        Future.value(Arrays.copyOfRange(memoryBuffer.getArray(), 0, memoryBuffer.length()))
      } finally {
        resetBuffer(memoryBuffer)
      }
    } catch {
      case e: Exception => Future.exception(e)
    }
  }

  final def apply(request: Array[Byte]): Future[Array[Byte]] = {
    val inputTransport = new TMemoryInputTransport(request)
    val iprot = protocolFactory.getProtocol(inputTransport)

    try {
      val msg = iprot.readMessageBegin()
      val func = functionMap.get(msg.name)
      func match {
        case _root_.scala.Some(fn) =>
          fn(iprot, msg.seqid)
        case _ =>
          TProtocolUtil.skip(iprot, TType.STRUCT)
          exception(msg.name, msg.seqid, TApplicationException.UNKNOWN_METHOD,
            "Invalid method name: '" + msg.name + "'")
      }
    } catch {
      case e: Exception => Future.exception(e)
    }
  }

  // ---- end boilerplate.

  addFunction("storeDependencies", { (iprot: TProtocol, seqid: Int) =>
    try {
      val args = StoreDependencies.Args.decode(iprot)
      iprot.readMessageEnd()
      (try {
        iface.storeDependencies(args.dependencies)
      } catch {
        case e: Exception => Future.exception(e)
      }) flatMap { value: Unit =>
        reply("storeDependencies", seqid, StoreDependencies.Result())
      } rescue {
        case e: com.twitter.zipkin.thriftscala.DependenciesException => {
          reply("storeDependencies", seqid, StoreDependencies.Result(e = Some(e)))
        }
        case e => Future.exception(e)
      }
    } catch {
      case e: TProtocolException => {
        iprot.readMessageEnd()
        exception("storeDependencies", seqid, TApplicationException.PROTOCOL_ERROR, e.getMessage)
      }
      case e: Exception => Future.exception(e)
    }
  })
  addFunction("getDependencies", { (iprot: TProtocol, seqid: Int) =>
    try {
      val args = GetDependencies.Args.decode(iprot)
      iprot.readMessageEnd()
      (try {
        iface.getDependencies(args.startTime, args.endTime)
      } catch {
        case e: Exception => Future.exception(e)
      }) flatMap { value: com.twitter.zipkin.thriftscala.Dependencies =>
        reply("getDependencies", seqid, GetDependencies.Result(success = Some(value)))
      } rescue {
        case e: com.twitter.zipkin.thriftscala.DependenciesException => {
          reply("getDependencies", seqid, GetDependencies.Result(qe = Some(e)))
        }
        case e => Future.exception(e)
      }
    } catch {
      case e: TProtocolException => {
        iprot.readMessageEnd()
        exception("getDependencies", seqid, TApplicationException.PROTOCOL_ERROR, e.getMessage)
      }
      case e: Exception => Future.exception(e)
    }
  })
}