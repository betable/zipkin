/**
 * Generated by Scrooge
 *   version: 4.0.0
 *   rev: 2d9d7656d3b3b7eff89450ac6a78f12af6cc627b
 *   built at: 20150828-134418
 */
package com.twitter.zipkin.thriftscala

import com.twitter.finagle.{SourcedException, Service}
import com.twitter.finagle.stats.{NullStatsReceiver, StatsReceiver}
import com.twitter.finagle.thrift.{Protocols, ThriftClientRequest}
import com.twitter.scrooge.{ThriftStruct, ThriftStructCodec}
import com.twitter.util.{Future, Return, Throw, Throwables}
import java.nio.ByteBuffer
import java.util.Arrays
import org.apache.thrift.protocol._
import org.apache.thrift.TApplicationException
import org.apache.thrift.transport.{TMemoryBuffer, TMemoryInputTransport}
import scala.collection.{Map, Set}

import scala.language.higherKinds


@javax.annotation.Generated(value = Array("com.twitter.scrooge.Compiler"))
class DependencySource$FinagleClient(
    val service: Service[ThriftClientRequest, Array[Byte]],
    val protocolFactory: TProtocolFactory = Protocols.binaryFactory(),
    val serviceName: String = "DependencySource",
    stats: StatsReceiver = NullStatsReceiver
) extends DependencySource[Future] {
  import DependencySource._

  protected def encodeRequest(name: String, args: ThriftStruct) = {
    val buf = new TMemoryBuffer(512)
    val oprot = protocolFactory.getProtocol(buf)

    oprot.writeMessageBegin(new TMessage(name, TMessageType.CALL, 0))
    args.write(oprot)
    oprot.writeMessageEnd()

    val bytes = Arrays.copyOfRange(buf.getArray, 0, buf.length)
    new ThriftClientRequest(bytes, false)
  }

  protected def decodeResponse[T <: ThriftStruct](resBytes: Array[Byte], codec: ThriftStructCodec[T]) = {
    val iprot = protocolFactory.getProtocol(new TMemoryInputTransport(resBytes))
    val msg = iprot.readMessageBegin()
    try {
      if (msg.`type` == TMessageType.EXCEPTION) {
        val exception = TApplicationException.read(iprot) match {
          case sourced: SourcedException =>
            if (serviceName != "") sourced.serviceName = serviceName
            sourced
          case e => e
        }
        throw exception
      } else {
        codec.decode(iprot)
      }
    } finally {
      iprot.readMessageEnd()
    }
  }

  protected def missingResult(name: String) = {
    new TApplicationException(
      TApplicationException.MISSING_RESULT,
      name + " failed: unknown result"
    )
  }

  protected def setServiceName(ex: Throwable): Throwable =
    if (this.serviceName == "") ex
    else {
      ex match {
        case se: SourcedException =>
          se.serviceName = this.serviceName
          se
        case _ => ex
      }
    }

  // ----- end boilerplate.

  private[this] val scopedStats = if (serviceName != "") stats.scope(serviceName) else stats
  private[this] object __stats_getDependencies {
    val RequestsCounter = scopedStats.scope("getDependencies").counter("requests")
    val SuccessCounter = scopedStats.scope("getDependencies").counter("success")
    val FailuresCounter = scopedStats.scope("getDependencies").counter("failures")
    val FailuresScope = scopedStats.scope("getDependencies").scope("failures")
  }
  /**
       * Get an aggregate representation of all services paired with every service they call in to.
       * This includes information on call counts and mean/stdDev/etc of call durations.  The two arguments
       * specify epoch time in microseconds. The end time is optional and defaults to one day after the
       * start time.
       */
  def getDependencies(startTime: Option[Long] = None, endTime: Option[Long] = None): Future[com.twitter.zipkin.thriftscala.Dependencies] = {
    __stats_getDependencies.RequestsCounter.incr()
    this.service(encodeRequest("getDependencies", GetDependencies.Args(startTime, endTime))) flatMap { response =>
      val result = decodeResponse(response, GetDependencies.Result)
      val exception: Future[Nothing] =
        if (false)
          null // can never happen, but needed to open a block
        else if (result.qe.isDefined)
          Future.exception(setServiceName(result.qe.get))
        else
          null
  
      if (result.success.isDefined)
        Future.value(result.success.get)
      else if (exception != null)
        exception
      else
        Future.exception(missingResult("getDependencies"))
    } respond {
      case Return(_) =>
        __stats_getDependencies.SuccessCounter.incr()
      case Throw(ex) =>
        setServiceName(ex)
        __stats_getDependencies.FailuresCounter.incr()
        __stats_getDependencies.FailuresScope.counter(Throwables.mkString(ex): _*).incr()
    }
  }
}
