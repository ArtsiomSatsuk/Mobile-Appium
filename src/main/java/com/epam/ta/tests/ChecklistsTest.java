package com.epam.ta.tests;

import com.epam.ta.config.Environment;
import com.epam.ta.core.driver.Driver;
import com.epam.ta.core.emulator.Emulator;
import com.epam.ta.core.listeners.Listener;
import com.epam.ta.core.service.dataproviders.DataProviders;
import com.epam.ta.core.service.helpers.ConnectionHandler;
import com.epam.ta.fixture.CommonConditions;
import com.epam.ta.product.bo.Checklist;
import com.epam.ta.steps.ManageAccountSteps;
import io.qameta.allure.Description;
import org.aeonbits.owner.ConfigFactory;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(Listener.class)
public class ChecklistsTest extends CommonConditions {

    private static final String PROPERTY_KEY = "deviceName";
    private static final String DEFAULT_DEVICE_VALUE = "nexus";

    private Emulator emulator;

    @BeforeSuite
    @Parameters({PROPERTY_KEY})
    public void startAppiumServer(@Optional(DEFAULT_DEVICE_VALUE) String propertyValue) {
        ConfigFactory.setProperty(PROPERTY_KEY, propertyValue);
        Environment env = ConfigFactory.create(Environment.class);
        ConnectionHandler.makePortAvailableIfOccupied(env.getAppiumPort());
        ConnectionHandler.makePortAvailableIfOccupied(env.getEmulatorPort());
        emulator = new Emulator(env.getEmulatorPort(), env.getEmulatorName());
        emulator.run();
        Driver.getDriverInstance().setEnv(env);

        try {
            Thread.sleep(45000);//TODO(костыль, по-другому не получается)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //adb wait-for-device ждёт когда станёт состояние девайса = device, но всё равно устройство перезагружается и adb не видит эмулятора
        //adb get-state ------ показывает state = device, но всё равно по
        //том устройствор перезагружается и adb не видит эмулятора
        //единственный вариант по тупорылому ждать тред слипом секунд 60 пока устройство нормально перезагрузится и adb найдёт эмулятор, обычно секунд 45 хватает
    }

    @Description("Checklist exists after creation")
    @Test(dataProvider = "checklists", dataProviderClass = DataProviders.class)
    public void checkChecklistExistence(Checklist checklist) {
        boolean actualResult = new ManageAccountSteps()
                .openEntryPage()
                .createChecklistWithoutTasks(checklist)
                .isChecklistExists(checklist);
        Assert.assertTrue(actualResult, String.format("[Checklist with name - %s doesn't exist after creation]", checklist.getName()));
    }

    @Description("check existence of tasks in checklist after creation")
    @Test(dataProvider = "checklists and tasks", dataProviderClass = DataProviders.class)
    public void checkTasksExistenceInChecklist(Checklist checklist, String[] tasks) {
        new ManageAccountSteps()
                .openEntryPage()
                .createChecklistWithTasks(checklist, tasks)
                .areAllTasksExistInChecklist(tasks)
                .assertAll();
    }

    @AfterSuite
    public void closeAppiumServer() {
        Driver.getDriverInstance().stopAppium();
        emulator.close();
    }

}