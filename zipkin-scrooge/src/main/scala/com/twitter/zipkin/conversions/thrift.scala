/*
 * Copyright 2012 Twitter Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.twitter.zipkin.conversions

import com.twitter.zipkin.common._
import com.twitter.zipkin.query._
import com.twitter.zipkin.thriftscala
import scala.collection.breakOut
import scala.language.implicitConversions

/**
 * Convenience implicits for converting between common classes and Thrift.
 */
object thrift {
  /* Endpoint */
  class ThriftEndpoint(e: Endpoint) {
    lazy val toThrift = thriftscala.Endpoint(e.ipv4, e.port, e.serviceName)
  }
  class WrappedEndpoint(e: thriftscala.Endpoint) {
    lazy val toEndpoint = {
      val serviceName = e.serviceName match {
        case (null | "") => Endpoint.UnknownServiceName
        case _ => e.serviceName
      }
      new Endpoint(e.ipv4, e.port, serviceName)
    }
  }
  implicit def endpointToThriftEndpoint(e: Endpoint) = new ThriftEndpoint(e)
  implicit def thriftEndpointToEndpoint(e: thriftscala.Endpoint) = new WrappedEndpoint(e)

  /* AnnotationType */
  class ThriftAnnotationType(a: AnnotationType) {
    lazy val toThrift = thriftscala.AnnotationType(a.value)
  }
  class WrappedAnnotationType(a: thriftscala.AnnotationType) {
    lazy val toAnnotationType = AnnotationType(a.value, a.name)
  }
  implicit def annotationTypeToThriftAnnotationType(a: AnnotationType) = new ThriftAnnotationType(a)
  implicit def thriftAnnotationTypeToAnnotationType(a: thriftscala.AnnotationType) = new WrappedAnnotationType(a)

  /* Annotation */
  class ThriftAnnotation(a: Annotation) {
    lazy val toThrift = {
      thriftscala.Annotation(a.timestamp, a.value, a.host.map(_.toThrift))
    }
  }
  class WrappedAnnotation(a: thriftscala.Annotation) {
    lazy val toAnnotation = {
      if (a.timestamp <= 0)
        throw new IllegalArgumentException("Annotation must have a timestamp: %s".format(a.toString))

      if ("".equals(a.value))
        throw new IllegalArgumentException("Annotation must have a value: %s".format(a.toString))

      new Annotation(a.timestamp, a.value, a.host.map(_.toEndpoint))
    }
  }
  implicit def annotationToThriftAnnotation(a: Annotation) = new ThriftAnnotation(a)
  implicit def thriftAnnotationToAnnotation(a: thriftscala.Annotation) = new WrappedAnnotation(a)

  /* BinaryAnnotation */
  class ThriftBinaryAnnotation(b: BinaryAnnotation) {
    lazy val toThrift = {
      thriftscala.BinaryAnnotation(b.key, b.value, b.annotationType.toThrift, b.host.map(_.toThrift))
    }
  }
  class WrappedBinaryAnnotation(b: thriftscala.BinaryAnnotation) {
    lazy val toBinaryAnnotation = {
      BinaryAnnotation(b.key, b.value, b.annotationType.toAnnotationType, b.host.map(_.toEndpoint))
    }
  }
  implicit def binaryAnnotationToThriftBinaryAnnotation(b: BinaryAnnotation) = new ThriftBinaryAnnotation(b)
  implicit def thriftBinaryAnnotationToBinaryAnnotation(b: thriftscala.BinaryAnnotation) = new WrappedBinaryAnnotation(b)

  /* Span */
  class ThriftSpan(s: Span) {
    lazy val toThrift = {
      thriftscala.Span(s.traceId, s.name, s.id, s.parentId, s.annotations.map { _.toThrift },
        s.binaryAnnotations.map { _.toThrift }, s.debug)
    }
  }
  class WrappedSpan(s: thriftscala.Span) {
    lazy val toSpan = {
      s.name match {
        case null => throw new IncompleteTraceDataException("No name set in Span")
        case _ => ()
      }

      Span(
        s.traceId,
        s.name,
        s.id,
        s.parentId,
        s.annotations match {
          case null => List.empty[Annotation]
          case as => as.map(_.toAnnotation)(breakOut)
        },
        s.binaryAnnotations match {
          case null => List.empty[BinaryAnnotation]
          case b => b.map(_.toBinaryAnnotation)(breakOut)
        },
        s.debug
      )
    }
  }
  implicit def spanToThriftSpan(s: Span) = new ThriftSpan(s)
  implicit def thriftSpanToSpan(s: thriftscala.Span) = new WrappedSpan(s)

  /* Trace */
  class WrappedTrace(t: Trace) {
    lazy val toThrift = thriftscala.Trace(t.spans.map{ _.toThrift })
  }
  class ThriftTrace(t: thriftscala.Trace) {
    lazy val toTrace = Trace(t.spans.map { _.toSpan })
  }
  implicit def traceToThrift(t: Trace) = new WrappedTrace(t)
  implicit def thriftToTrace(t: thriftscala.Trace) = new ThriftTrace(t)

  class WrappedDependencyLink(dl: DependencyLink) {
    lazy val toThrift = thriftscala.DependencyLink(dl.parent, dl.child, dl.callCount)
  }
  class ThriftDependencyLink(dl: thriftscala.DependencyLink) {
    lazy val toDependencyLink = DependencyLink(dl.parent, dl.child, dl.callCount)
  }
  implicit def dependencyLinkToThrift(dl: DependencyLink) = new WrappedDependencyLink(dl)
  implicit def thriftToDependencyLink(dl: thriftscala.DependencyLink) = new ThriftDependencyLink(dl)
  class WrappedDependencies(d: Dependencies) {
    lazy val toThrift = thriftscala.Dependencies(d.startTime, d.endTime, d.links.map(_.toThrift))
  }
  class ThriftDependencies(d: thriftscala.Dependencies) {
    lazy val toDependencies = Dependencies(d.startTime, d.endTime, d.links.map(_.toDependencyLink))
  }
  implicit def dependenciesToThrift(d: Dependencies) = new WrappedDependencies(d)
  implicit def thriftToDependencies(d: thriftscala.Dependencies) = new ThriftDependencies(d)
}
