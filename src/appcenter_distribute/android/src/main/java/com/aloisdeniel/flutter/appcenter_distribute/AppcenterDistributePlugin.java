package com.aloisdeniel.flutter.appcenter_distribute;

import java.util.UUID;
import android.app.Application;

import com.microsoft.appcenter.distribute.Distribute;
import com.microsoft.appcenter.utils.async.AppCenterConsumer;

import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * AppcenterDistributePlugin
 */
public class AppcenterDistributePlugin implements MethodCallHandler {
  private Registrar registrar;

  private AppcenterDistributePlugin(Registrar registrar) {
    this.registrar = registrar;
  }

  /**
   * Plugin registration.
   */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "aloisdeniel.github.com/flutter_plugin_appcenter/appcenter_distribute");

    final AppcenterDistributePlugin plugin = new AppcenterDistributePlugin(registrar);
    channel.setMethodCallHandler(plugin);
  }

  @Override
  public void onMethodCall(MethodCall call, final Result result) {

    switch (call.method) {
      case "isEnabled":
        Distribute.isEnabled().thenAccept(new AppCenterConsumer<Boolean>() {
          @Override
          public void accept(Boolean enabled) {
            result.success(enabled.booleanValue());
          }
        });

        break;
      case "setEnabled":
        Boolean isEnabled = call.argument("isEnabled");
        Distribute.setEnabled(isEnabled).thenAccept(new AppCenterConsumer<Void>() {
          @Override
          public void accept(Void v) {
            result.success(null);
          }
        });
        break;
      default:
        result.notImplemented();
        break;
    }
  }
}
