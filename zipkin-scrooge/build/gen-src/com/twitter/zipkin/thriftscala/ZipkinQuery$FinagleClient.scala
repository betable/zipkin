/**
 * Generated by Scrooge
 *   version: 4.1.0
 *   rev: 87b84f89477a4737c8d57580a1e8bdaeac529b19
 *   built at: 20150928-114808
 */
package com.twitter.zipkin.thriftscala

import com.twitter.finagle.SourcedException
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
class ZipkinQuery$FinagleClient(
    val service: com.twitter.finagle.Service[ThriftClientRequest, Array[Byte]],
    val protocolFactory: TProtocolFactory = Protocols.binaryFactory(),
    val serviceName: String = "ZipkinQuery",
    stats: StatsReceiver = NullStatsReceiver
) extends ZipkinQuery[Future] {
  import ZipkinQuery._

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
  private[this] object __stats_getTracesByIds {
    val RequestsCounter = scopedStats.scope("getTracesByIds").counter("requests")
    val SuccessCounter = scopedStats.scope("getTracesByIds").counter("success")
    val FailuresCounter = scopedStats.scope("getTracesByIds").counter("failures")
    val FailuresScope = scopedStats.scope("getTracesByIds").scope("failures")
  }
  /** Results are sorted in order of the first span's timestamp */
  def getTracesByIds(traceIds: Seq[Long] = Seq[Long](), adjustClockSkew: Boolean = true): Future[Seq[com.twitter.zipkin.thriftscala.Trace]] = {
    __stats_getTracesByIds.RequestsCounter.incr()
    this.service(encodeRequest("getTracesByIds", GetTracesByIds.Args(traceIds, adjustClockSkew))) flatMap { response =>
      val result = decodeResponse(response, GetTracesByIds.Result)
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
        Future.exception(missingResult("getTracesByIds"))
    } respond {
      case Return(_) =>
        __stats_getTracesByIds.SuccessCounter.incr()
      case Throw(ex) =>
        setServiceName(ex)
        __stats_getTracesByIds.FailuresCounter.incr()
        __stats_getTracesByIds.FailuresScope.counter(Throwables.mkString(ex): _*).incr()
    }
  }
  private[this] object __stats_getTraces {
    val RequestsCounter = scopedStats.scope("getTraces").counter("requests")
    val SuccessCounter = scopedStats.scope("getTraces").counter("success")
    val FailuresCounter = scopedStats.scope("getTraces").counter("failures")
    val FailuresScope = scopedStats.scope("getTraces").scope("failures")
  }
  /** Results are sorted in order of the first span's timestamp */
  def getTraces(request: com.twitter.zipkin.thriftscala.QueryRequest): Future[Seq[com.twitter.zipkin.thriftscala.Trace]] = {
    __stats_getTraces.RequestsCounter.incr()
    this.service(encodeRequest("getTraces", GetTraces.Args(request))) flatMap { response =>
      val result = decodeResponse(response, GetTraces.Result)
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
        Future.exception(missingResult("getTraces"))
    } respond {
      case Return(_) =>
        __stats_getTraces.SuccessCounter.incr()
      case Throw(ex) =>
        setServiceName(ex)
        __stats_getTraces.FailuresCounter.incr()
        __stats_getTraces.FailuresScope.counter(Throwables.mkString(ex): _*).incr()
    }
  }
  private[this] object __stats_getServiceNames {
    val RequestsCounter = scopedStats.scope("getServiceNames").counter("requests")
    val SuccessCounter = scopedStats.scope("getServiceNames").counter("success")
    val FailuresCounter = scopedStats.scope("getServiceNames").counter("failures")
    val FailuresScope = scopedStats.scope("getServiceNames").scope("failures")
  }
  /**
       * Fetch all the service names we have seen from now all the way back to the set ttl.
       */
  def getServiceNames(): Future[Set[String]] = {
    __stats_getServiceNames.RequestsCounter.incr()
    this.service(encodeRequest("getServiceNames", GetServiceNames.Args())) flatMap { response =>
      val result = decodeResponse(response, GetServiceNames.Result)
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
        Future.exception(missingResult("getServiceNames"))
    } respond {
      case Return(_) =>
        __stats_getServiceNames.SuccessCounter.incr()
      case Throw(ex) =>
        setServiceName(ex)
        __stats_getServiceNames.FailuresCounter.incr()
        __stats_getServiceNames.FailuresScope.counter(Throwables.mkString(ex): _*).incr()
    }
  }
  private[this] object __stats_getSpanNames {
    val RequestsCounter = scopedStats.scope("getSpanNames").counter("requests")
    val SuccessCounter = scopedStats.scope("getSpanNames").counter("success")
    val FailuresCounter = scopedStats.scope("getSpanNames").counter("failures")
    val FailuresScope = scopedStats.scope("getSpanNames").scope("failures")
  }
  /**
       * Get all the seen span names for a particular service, from now back until the set ttl.
       */
  def getSpanNames(serviceName: String): Future[Set[String]] = {
    __stats_getSpanNames.RequestsCounter.incr()
    this.service(encodeRequest("getSpanNames", GetSpanNames.Args(serviceName))) flatMap { response =>
      val result = decodeResponse(response, GetSpanNames.Result)
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
        Future.exception(missingResult("getSpanNames"))
    } respond {
      case Return(_) =>
        __stats_getSpanNames.SuccessCounter.incr()
      case Throw(ex) =>
        setServiceName(ex)
        __stats_getSpanNames.FailuresCounter.incr()
        __stats_getSpanNames.FailuresScope.counter(Throwables.mkString(ex): _*).incr()
    }
  }
}
