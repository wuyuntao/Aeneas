cd "$PSScriptRoot\.."

sbt package # Compile all packages

sbt ";project migrationExample; run"