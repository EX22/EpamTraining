The Project's technical description.

    The application shows how we can get cargo service for some amount of ships
in a port with restricted amount of docks(cargo service points). The Dock is
the thread in this application. It's created for each next ship but their(docks)
amount can not be greater than Port's AmountOfDocks field value.Dock threads
perform loading and unloading operations for every ship, one ship per dock at
the time.
    The method runExperiment in the Main class launch one port's system, based
on passed to it parameters that were read from the text file. If there are
enough data sets in the text file we can launch several port's system
consequently with different sets of data by runExperiment's method invocation.
    There should be text file with data in appropriate format for port system
creation and start the application. An appropriate data example shown in the
CorrectData.txt file, basically it's JSON format.