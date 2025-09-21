#! /bin/bash

find target/classes -name '*.class' | while read -r classfile; do
  # Remove path prefix and make path relative
  relpath="${classfile#target/classes/}"

  # Replace extension and put under output directory
  outfile="asm/${relpath%.class}.jasm"

  # Ensure output subdirectory exists
  mkdir -p "$(dirname "$outfile")"

  # Run javap and save output
  javap -p -v "$classfile" > "$outfile"
done
