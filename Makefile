dist:
	./bin/sbt zipkin-collector-service/package-dist
	./bin/sbt zipkin-query-service/package-dist
	./bin/sbt zipkin-web/package-dist

.PHONY: dist
