#!/usr/bin/env bash

#stack_name="sample-cloudwatch-errorhandling-lambda"
#stack_name="sample-apigateway-errorhandling-lambda"
stack_name="sample-lambda-errorhandling-lambda"

aws cloudformation delete-stack --stack-name ${stack_name}