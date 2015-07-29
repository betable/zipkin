/*
 * Copyright 2012 Twitter Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package com.twitter.zipkin.sampler

import com.twitter.finagle.Service
import com.twitter.finagle.httpx.{HttpMuxer, Method, Request, Response}
import com.twitter.util.{Extractable, Future, Var}

/**
 * A wrapper around a Var[Double] that exposes it to an HTTP endpoint
 * at "/vars/`name`". The Var can be read via a GET request and
 * updated via a POST request. The body of the POST will be used
 * as the new value.
 */
class HttpVar(name: String, default: Double = 1.0) {
  private[this] val underlying = Var(default)

  def apply(): Var[Double] with Extractable[Double] = underlying

  HttpMuxer.addRichHandler("/vars/"+name, Service.mk[Request, Response] {
    case req if req.method == Method.Get =>
      req.response.contentString = underlying().toString
      Future.value(req.response)

    case req if req.method == Method.Post =>
      val rep = req.response

      try {
        val newRate = req.contentString.toDouble
        if (newRate > 1 || newRate < 0) {
          rep.statusCode = 400
          rep.contentString = "invalid rate"
        } else {
          underlying.update(newRate)
          rep.contentString = newRate.toString
        }
      } catch {
        case e: Exception =>
          rep.statusCode = 500
          rep.contentString = e.toString
      }

      Future.value(rep)

    case req =>
      req.response.statusCode = 404
      Future.value(req.response)
  })
}
