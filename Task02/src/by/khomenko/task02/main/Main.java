package by.khomenko.task02.main;

import by.khomenko.task02.entity.Container;
import by.khomenko.task02.exception.DataReaderException;
import by.khomenko.task02.exception.ValidationException;
import by.khomenko.task02.factory.FleetCreator;
import by.khomenko.task02.factory.PortCreator;
import by.khomenko.task02.logic.Dock;
import by.khomenko.task02.entity.Port;
import by.khomenko.task02.entity.Ship;
import by.khomenko.task02.parser.ExperimentDataParser;
import by.khomenko.task02.reader.DataReader;
import by.khomenko.task02.validator.ExperimentDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Runs the application.
 */
public class Main {

    //TODO Find out from teacher about singleton.

    //TODO Add into README.md file project's technical description
    // ( what is the thread in this app, is there any restriction etc.)
    // Add UML diagram to the project.

    /**
     * File path for reading data for apps functioning.
     */
    private static final String FILE_PATH
            = ".\\out\\production\\Task02\\data\\CorrectData.txt";

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    /**
     * @param args parameters passing into the method.
     * @throws InterruptedException if the current thread is interrupted.
     */
    public static void main(final String[] args) throws InterruptedException {

        Path path = Paths.get(FILE_PATH);
        DataReader dataReader = new DataReader();
        ExperimentDataParser experimentDataParser = new ExperimentDataParser();
        List<Map<String, Object>> parametersList = new ArrayList<>();
        ExperimentDataValidator experimentDataValidator
                = new ExperimentDataValidator();

        try {

            parametersList
                    = experimentDataParser.parseData(dataReader.readData(path));

        } catch (ValidationException e) {
            String message = "Data read from file is not valid.";
            LOGGER.warn(message, e);
        } catch (DataReaderException e) {
            String message = "During the file reading an exception occurred.";
            LOGGER.warn(message, e);
        }

        for (Map<String, Object> parameters : parametersList) {

            if (experimentDataValidator.validateExperiment(parameters)) {
                runExperiment((Map<String, Object>) parameters.get("PortData"),
                        (Map<String, Object>) parameters.get("FleetData"));
            }
        }
    }

    public static void runExperiment(Map<String, Object> portData,
                                     Map<String, Object> fleetData)
            throws InterruptedException {

        PortCreator portCreator = new PortCreator();
        FleetCreator fleetCreator = new FleetCreator();

        Port port = portCreator.createPort(portData);
        Ship[] fleet = fleetCreator.createFleet(fleetData);


        for (Ship ship1 : fleet) {
            for (int j = 0; j < ship1.getStorage().getCapacity(); j++) {
                ship1.getStorage().put(new Container("container No " + j));
            }
        }

        ExecutorService executor
                = Executors.newFixedThreadPool(port.getAmountOfDocks());

        List<Future<String>> list = new ArrayList<>();

        for (Ship ship : fleet) {
            Callable<String> callable = new Dock(port, ship);
            Future<String> future = executor.submit(callable);
            list.add(future);
        }

        for (Future<String> fut : list) {
            try {

                LOGGER.info(fut.get());

                //TODO Find out from teacher about releasing resources here.

            } catch (InterruptedException | ExecutionException e) {
                String message = "Some exception's message here is"
                        + " in the Main class : ";
                LOGGER.warn(message);
            }
        }

        executor.shutdown();

    }
}
