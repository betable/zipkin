dist:
	./gradlew installDist
	mv zipkin-collector-service/build/install zipkin-collector-service/install
	mv zipkin-query-service/build/install zipkin-query-service/install
	mv zipkin-web/build/install zipkin-web/install

.PHONY: dist
