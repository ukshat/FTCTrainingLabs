/*
 * Copyright (c) 2019 OpenFTC Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package CupertinoRobotics.support.EasyCV.OpenCvCameraManagment;

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.qualcomm.robotcore.eventloop.opmode.AnnotatedOpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegistrar;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.internal.camera.delegating.SwitchableCameraName;
import org.firstinspires.ftc.robotcore.internal.system.AppUtil;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

class OpenCvCameraFactoryImpl extends OpenCvCameraFactory
{
    private static int appVersion = -1;
    private static int sdk6_1_versionCode = 39;
    private static String sdk_6_1 = "6.1";

    static void init()
    {
        OpenCvCameraFactory.theInstance = new OpenCvCameraFactoryImpl();

        try
        {
            appVersion = AppUtil.getDefContext().getPackageManager().getPackageInfo(AppUtil.getDefContext().getPackageName(), 0).versionCode;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    @OpModeRegistrar
    public static void initOnSdkBoot(Context context, AnnotatedOpModeManager manager)
    {
        init();
    }

    private void throwIfIncompatibleSdkVersion()
    {
        if(appVersion < sdk6_1_versionCode)
        {
            throw new RuntimeException(String.format("EasyOpenCV is only compatible with SDK v%s or greater!", sdk_6_1));
        }
    }

    @Override
    public OpenCvInternalCamera createInternalCamera(OpenCvInternalCamera.CameraDirection direction)
    {
        throwIfIncompatibleSdkVersion();
        return new OpenCvInternalCameraImpl(direction);
    }

    @Override
    public OpenCvInternalCamera2 createInternalCamera2(OpenCvInternalCamera2.CameraDirection direction)
    {
        throwIfIncompatibleSdkVersion();
        return new OpenCvInternalCamera2Impl(direction);
    }

    @Override
    public OpenCvWebcam createWebcam(WebcamName webcamName)
    {
        throwIfIncompatibleSdkVersion();
        return new OpenCvWebcamImpl(webcamName);
    }

    @Override
    public OpenCvSwitchableWebcam createSwitchableWebcam(WebcamName... names)
    {
        throwIfIncompatibleSdkVersion();
        SwitchableCameraName cameraName = ClassFactory.getInstance().getCameraManager().nameForSwitchableCamera(names);

        return new OpenCvSwitchableWebcamImpl(cameraName);
    }

    @Override
    public OpenCvSwitchableWebcam createSwitchableWebcam(int viewportContainerId, WebcamName... names)
    {
        throwIfIncompatibleSdkVersion();
        SwitchableCameraName cameraName = ClassFactory.getInstance().getCameraManager().nameForSwitchableCamera(names);

        return new OpenCvSwitchableWebcamImpl(cameraName, viewportContainerId);
    }

    @Override
    public OpenCvCamera createVuforiaPassthrough(VuforiaLocalizer vuforiaLocalizer, VuforiaLocalizer.Parameters parameters)
    {
        return new OpenCvVuforiaPassthroughImpl(vuforiaLocalizer, parameters);
    }
}
