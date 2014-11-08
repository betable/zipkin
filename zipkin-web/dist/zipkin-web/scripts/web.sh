#!/bin/sh
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
VERSION=1.2.0-SNAPSHOT

java -cp "$DIR/../libs/*" -jar $DIR/../zipkin-web-$VERSION.jar $@
