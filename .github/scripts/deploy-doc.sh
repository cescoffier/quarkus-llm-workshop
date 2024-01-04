#!/bin/bash

echo "🚧 Building..."

cd docs
pipenv run mkdocs gh-deploy --force

echo "🍺 Site updated!"
