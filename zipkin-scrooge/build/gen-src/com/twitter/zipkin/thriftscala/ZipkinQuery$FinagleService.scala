/**
 * Generated by Scrooge
 *   version: 4.0.0
 *   rev: 2d9d7656d3b3b7eff89450ac6a78f12af6cc627b
 *   built at: 20150828-134418
 */
package com.twitter.zipkin.thriftscala

import com.twitter.finagle.{Service, Thrift}
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
class ZipkinQuery$FinagleService(
  iface: ZipkinQuery[Future],
  protocolFactory: TProtocolFactory,
  stats: StatsReceiver,
  maxThriftBufferSize: Int
) extends Service[Array[Byte], Array[Byte]] {
  import ZipkinQuery._

  def this(
    iface: ZipkinQuery[Future],
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

  addFunction("getTracesByIds", { (iprot: TProtocol, seqid: Int) =>
    try {
      val args = GetTracesByIds.Args.decode(iprot)
      iprot.readMessageEnd()
      (try {
        iface.getTracesByIds(args.traceIds, args.adjustClockSkew)
      } catch {
        case e: Exception => Future.exception(e)
      }) flatMap { value: Seq[com.twitter.zipkin.thriftscala.Trace] =>
        reply("getTracesByIds", seqid, GetTracesByIds.Result(success = Some(value)))
      } rescue {
        case e: com.twitter.zipkin.thriftscala.QueryException => {
          reply("getTracesByIds", seqid, GetTracesByIds.Result(qe = Some(e)))
        }
        case e => Future.exception(e)
      }
    } catch {
      case e: TProtocolException => {
        iprot.readMessageEnd()
        exception("getTracesByIds", seqid, TApplicationException.PROTOCOL_ERROR, e.getMessage)
      }
      case e: Exception => Future.exception(e)
    }
  })
  addFunction("getTraces", { (iprot: TProtocol, seqid: Int) =>
    try {
      val args = GetTraces.Args.decode(iprot)
      iprot.readMessageEnd()
      (try {
        iface.getTraces(args.request)
      } catch {
        case e: Exception => Future.exception(e)
      }) flatMap { value: Seq[com.twitter.zipkin.thriftscala.Trace] =>
        reply("getTraces", seqid, GetTraces.Result(success = Some(value)))
      } rescue {
        case e: com.twitter.zipkin.thriftscala.QueryException => {
          reply("getTraces", seqid, GetTraces.Result(qe = Some(e)))
        }
        case e => Future.exception(e)
      }
    } catch {
      case e: TProtocolException => {
        iprot.readMessageEnd()
        exception("getTraces", seqid, TApplicationException.PROTOCOL_ERROR, e.getMessage)
      }
      case e: Exception => Future.exception(e)
    }
  })
  addFunction("getServiceNames", { (iprot: TProtocol, seqid: Int) =>
    try {
      val args = GetServiceNames.Args.decode(iprot)
      iprot.readMessageEnd()
      (try {
        iface.getServiceNames()
      } catch {
        case e: Exception => Future.exception(e)
      }) flatMap { value: Set[String] =>
        reply("getServiceNames", seqid, GetServiceNames.Result(success = Some(value)))
      } rescue {
        case e: com.twitter.zipkin.thriftscala.QueryException => {
          reply("getServiceNames", seqid, GetServiceNames.Result(qe = Some(e)))
        }
        case e => Future.exception(e)
      }
    } catch {
      case e: TProtocolException => {
        iprot.readMessageEnd()
        exception("getServiceNames", seqid, TApplicationException.PROTOCOL_ERROR, e.getMessage)
      }
      case e: Exception => Future.exception(e)
    }
  })
  addFunction("getSpanNames", { (iprot: TProtocol, seqid: Int) =>
    try {
      val args = GetSpanNames.Args.decode(iprot)
      iprot.readMessageEnd()
      (try {
        iface.getSpanNames(args.serviceName)
      } catch {
        case e: Exception => Future.exception(e)
      }) flatMap { value: Set[String] =>
        reply("getSpanNames", seqid, GetSpanNames.Result(success = Some(value)))
      } rescue {
        case e: com.twitter.zipkin.thriftscala.QueryException => {
          reply("getSpanNames", seqid, GetSpanNames.Result(qe = Some(e)))
        }
        case e => Future.exception(e)
      }
    } catch {
      case e: TProtocolException => {
        iprot.readMessageEnd()
        exception("getSpanNames", seqid, TApplicationException.PROTOCOL_ERROR, e.getMessage)
      }
      case e: Exception => Future.exception(e)
    }
  })
}