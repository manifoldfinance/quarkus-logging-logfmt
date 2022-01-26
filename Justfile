# just is a handy way to save and run project-specific commands.
#
# https://github.com/casey/just

default:
  just --list

# Build the whole project
alias b := build
build:
  bin/build.sh

# Cleans the project
clean:
  ./gradlew clean

# Configure the docker-compose topics
alias c := configure
configure:
  bin/configure.sh

# Useful if you want to pull docker images from the registry
ecr-login:
  bin/ecr-login.sh

# Start docker-compose
up *ARGS:
  docker-compose --compatibility up {{ARGS}}

# Start docker-compose
start *ARGS:
  docker-compose start {{ARGS}}

# Stop docker-compose
stop *ARGS:
  docker-compose stop {{ARGS}}

# Stop docker-compose
down *ARGS:
  docker-compose down {{ARGS}}

# Display processes
ps *ARGS:
  docker-compose ps {{ARGS}}

# Display logs
logs *ARGS:
  docker-compose logs {{ARGS}}

# Format the code
alias f := fmt
fmt:
  treefmt

# Start IDEA in this folder
idea:
  nohup idea-ultimate . > /dev/null 2>&1 &
