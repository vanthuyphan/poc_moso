FROM jenkins/jenkins:lts

ENV DOCKER_VERSION=18.06.1-ce
ENV DOCKER_COMPOSE_VERSION=1.23.1

USER root

RUN curl -fsSLO https://download.docker.com/linux/static/stable/x86_64/docker-${DOCKER_VERSION}.tgz \
  && tar xzvf docker-${DOCKER_VERSION}.tgz --strip 1 -C /usr/local/bin docker/docker \
  && rm docker-${DOCKER_VERSION}.tgz \
  && curl -sL https://github.com/docker/compose/releases/download/${DOCKER_COMPOSE_VERSION}/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose \
  && chmod +x /usr/local/bin/docker-compose
