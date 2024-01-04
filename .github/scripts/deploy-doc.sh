#!/bin/bash

echo "🚧 Building..."
cd docs
pip install pipenv
pipenv install
pipenv run mkdocs gh-deploy --force

echo "🍺 Site updated!"
