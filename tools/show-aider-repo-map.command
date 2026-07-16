#!/bin/zsh
set -e

cd "$(dirname "$0")/.."

echo "Showing Aider's repo map for:"
pwd
echo

/Users/luckyxian/.local/bin/aider \
  --show-repo-map \
  --no-check-update \
  --no-analytics \
  --no-gitignore \
  --map-tokens 2048 \
  --model gpt-4o \
  --openai-api-key sk-dummy \
  --exit

echo
echo "Done. This command only shows the repo map; it does not chat with a model or edit files."
