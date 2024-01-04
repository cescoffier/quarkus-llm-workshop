#!/bin/bash

echo "🚧 Building..."
cd docs
pip install pipenv
pip install mkdocs mkdocs-material mkdocs-macros-plugin mike
mkdocs gh-deploy --force --clean --verbose

echo "🍺 Site updated!"
