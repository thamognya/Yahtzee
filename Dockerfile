FROM gradle:latest
COPY . /app
WORKDIR /app
RUN gradle build
CMD ["gradle", "run"]
