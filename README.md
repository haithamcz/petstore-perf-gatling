# petstore-perf-gatling

## Running the tests
In Terminal navigate to gatling/bin and run <code>./gatling.sh </code>
Test results should be generated to <b>/results</b>

## Test structure
in user-files/simulation the PetStore simulation is located which test load 5 users into the system for 
       
        -   POST endpoint
        -   GET  endpoint
        -   DEL  endpoint
The test start by loading <strong>pets.csv</strong> file for test data then execute each scenario one by one for each virtual user, furthermore I used basic scenario simulation from 
<strong>gatling.io</strong>   
I already ran those tests under IOs system and did not encounter any KOs for above tested endpoints for results of this execution please see them in <code>/gatling/results/petstoresimulation/index.html
</code>
    
