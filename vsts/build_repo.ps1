﻿mvn install -DskipITs=false "-Dfailsafe.rerunFailingTestsCount=2" -T 2C #parallelized to use 2 threads per core