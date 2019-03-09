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
public final class Main {

    //TODO Find out from teacher about singleton.

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
     * Private constructor without parameters.
     * As far as utility classes should not have a public or default
     * constructor.
     */
    private Main() {
    }

    /**
     * @param args parameters passing into the method.
     * @throws InterruptedException if the thread is interrupted.
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

    /**
     * Runs single port's system.
     *
     * @param portData  data for port's creation.
     * @param fleetData data for fleet's creation.
     * @throws InterruptedException if the thread is interrupted.
     */
    public static void runExperiment(final Map<String, Object> portData,
                                     final Map<String, Object> fleetData)
            throws InterruptedException {

        PortCreator portCreator = new PortCreator();
        FleetCreator fleetCreator = new FleetCreator();

        Port port = portCreator.createPort(portData);
        Ship[] fleet = fleetCreator.createFleet(fleetData);


        for (Ship shipUnit : fleet) {
            for (int j = 0; j < shipUnit.getStorage().getCapacity(); j++) {
                shipUnit.getStorage().put(new Container("container No " + j));
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
