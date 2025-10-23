#!/bin/bash

# 환경변수 또는 인자로부터 이미지 버전 설정
if [ -n "$IMAGE_VERSION" ]; then
  # 환경변수가 설정되어 있으면 사용
  echo "Using IMAGE_VERSION from environment: $IMAGE_VERSION"
elif [ -n "$1" ]; then
  # 인자가 제공되면 사용
  IMAGE_VERSION=$1
  echo "Using IMAGE_VERSION from argument: $IMAGE_VERSION"
else
  # 둘 다 없으면 에러
  echo "Error: IMAGE_VERSION not provided"
  echo "Usage: $0 <imageVersion>"
  echo "   or: IMAGE_VERSION=<imageVersion> $0"
  echo "Example: $0 beta-20251015-0001"
  echo "     or: IMAGE_VERSION=beta-20251015-0001 $0"
  exit 1
fi

# IMAGE_VERSION을 환경변수로 export (gradle에서 사용)
export IMAGE_VERSION

./gradlew :dailyfeed-member:jibDockerBuild --info
docker push alpha300uk/dailyfeed-member-svc:${IMAGE_VERSION}
