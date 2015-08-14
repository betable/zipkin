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
package com.twitter.zipkin.collector.processor

import com.twitter.finagle.Service
import com.twitter.util.{Await, Time, Future}

class FanoutService[-Req](services: Seq[Service[Req, Unit]]) extends Service[Req, Unit] {
  def apply(req: Req): Future[Unit] = {
    Future.join {
      services map { _.apply(req) }
    }
  }

  override def close(deadline: Time) = {
    val results = services map { _.close(deadline) }
    Await.result(Future.collect(results), Time.now - deadline)
    super.close(deadline)
  }
}
