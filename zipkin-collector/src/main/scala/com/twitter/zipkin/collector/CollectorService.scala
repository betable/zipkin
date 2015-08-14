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
package com.twitter.zipkin.collector

import com.twitter.ostrich.admin.Service
import com.twitter.zipkin.storage.Store

trait CollectorService extends Service {
  val writeQueue: WriteQueue[_ <: AnyRef]
  val stores: Seq[Store]

  @volatile var running = false

  def start() {
    running = true
  }

  def shutdown() {
    running = false
    writeQueue.shutdown()
    stores.foreach { store =>
      store.aggregates.close()
      store.spanStore.close()
    }
  }
}
