The Project's technical description.

    The application shows how we can get cargo service for some amount of ships
in a port with restricted amount of docks(cargo service points). The Dock is
the thread in this application. It's created for each next ship

    ExecutorService executor
                = Executors.newFixedThreadPool(port.getAmountOfDocks());
    . . .
    for (Ship ship : fleet) {
                Callable<String> callable = new Dock(port, ship);
    . . .
    }

but their(docks)amount can not be greater than Port's AmountOfDocks field value.
    Dock threads perform fully loading

    while (!ship.getStorage().isFull()) {
        ship.getStorage().put(port.getStorage().take());
    }

and fully unloading

    while (!ship.getStorage().isEmpty()) {
        port.getStorage().put(ship.getStorage().take());
    }

operations for every ship, one ship per dock at the time.
    The method runExperiment in the Main class launch one port's system,

    for (Ship ship : fleet) {
                Callable<String> callable = new Dock(port, ship);
                Future<String> future = executor.submit(callable);
                list.add(future);
            }

based on passed to it parameters that were read from the text file.
In the text file single line represents data for one port's system.
The Port class is a thread safe singleton here, it can't be created more than
once in case of running single port system but if there are enough data sets
in the text file we can launch several port's system consequently with
different sets of data using runExperiment's method invocation.
    There should be text file with data in appropriate format

    { "PortData": {"PortStorageSize" : 11000 , "AmountOfDocks" : 7 },
     "FleetData": {"Fleet" : 10 , "MinShipStorageSize" : 100 ,
      "MaxShipStorageSize" : 1000 }}

for port system creation and start the application. An appropriate data example
above has been taken from the CorrectData.txt file, basically it's JSON format.