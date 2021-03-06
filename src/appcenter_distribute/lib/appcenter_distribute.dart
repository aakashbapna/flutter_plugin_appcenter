import 'dart:async';

import 'package:flutter/services.dart';
import 'package:flutter/foundation.dart' show defaultTargetPlatform;
import 'package:flutter/foundation.dart' show TargetPlatform;

class AppCenterDistribute {
  static String get id => (defaultTargetPlatform == TargetPlatform.iOS)
      ? "MSDistribute"
      : "com.microsoft.appcenter.distribute.Distribute";

  static const MethodChannel _channel = const MethodChannel(
      'aloisdeniel.github.com/flutter_plugin_appcenter/appcenter_distribute');

  static Future<bool> get isEnabled async =>
      _channel.invokeMethod('isEnabled').then((r) => r as bool);

  static Future setEnabled(bool isEnabled) =>
      _channel.invokeMethod('setEnabled', <String, bool>{
        'isEnabled': isEnabled,
      });

}
