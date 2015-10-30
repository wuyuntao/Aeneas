$ProjectDir = "$PSScriptRoot\.."
$MigrationDir = "$PSScriptRoot\..\aeneas-migration-example\src\main\scala\com\wuyuntao\aeneas\migration\example\migrations"

cd $ProjectDir

sbt package # Compile all packages

sbt ";project migrationExample; run new -o $MigrationDir -n $args"