cd "$PSScriptRoot\.."

sbt package # Compile all packages

sbt ";project cli; run $args"
