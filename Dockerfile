FROM adoptopenjdk/openjdk11:jre-11.0.9_11.1-alpine

RUN  chmod a+rwx /var/log
RUN  addgroup -S 1000 && adduser -S 1000 -G 1000

USER 1000

VOLUME /tmp

#ARG DEPENDENCY=target/dependency
#COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
#COPY ${DEPENDENCY}/META-INF /app/META-INF
#COPY ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["sh", "-c", "java $JVM_OPTS -cp app:app/lib/* be.sgerard.demoa.gateway.GatewayServiceApplication"]
