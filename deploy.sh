#!/bin/bash
docker build -t biospheere/discordlog .
docker login -u "$DOCKER_USERNAME" -p "$DOCKER_PASSWORD"
docker push biospheere/discordlog