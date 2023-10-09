FROM amazoncorretto:17-alpine-jdk

# Add app user
ARG APPLICATION_USER=appuser
RUN adduser --no-create-home -u 1000 -D $APPLICATION_USER

# Configure working directory
RUN mkdir /app && \
    chown -R $APPLICATION_USER /app

USER 1000

COPY --chown=1000:1000 target/product-service-0.0.1-SNAPSHOT.jar /app/app.jar
WORKDIR /app

EXPOSE 9001
ENTRYPOINT [ "java", "-jar", "/app/app.jar" ]