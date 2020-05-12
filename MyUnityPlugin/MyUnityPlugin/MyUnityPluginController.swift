//
//  MyUnityPluginController.swift
//  MyUnityPlugin
//
//  Created by Andy.Kim on 2020/05/12.
//  Copyright © 2020 example. All rights reserved.
//

import Foundation

@objc
public protocol MyUnityPluginProtocol: class {
    @objc func OnLoad()
    @objc func OnCallTestFunc1(str: String)
    @objc func OnCallTestFunc2(num: String)
}

@objc
public class MyUnityPluginContoller: NSObject {
    static let shared = MyUnityPluginContoller()
    var pluginDelegate: MyUnityPluginProtocol?
    
    override init() {
        super.init()
    }
    
    deinit {
        NotificationCenter.default.removeObserver(self)
    }
    
    @objc public static func GetInstance() -> MyUnityPluginContoller {
        return shared
    }
    
    
    @objc public func Initialize(pluginDelegate: MyUnityPluginProtocol) {
        self.pluginDelegate = pluginDelegate
        self.pluginDelegate?.OnLoad()
    }
    
    
    @objc public func TestFunc1(str: String) -> String {
        print("TestFunc1 Called : " + str);
        pluginDelegate?.OnCallTestFunc1(str: str)
        return str;
    }
    
    @objc public func TestFunc2(num: Int) -> Int {
        print("TestFunc2 Called : " + String(num));
        pluginDelegate?.OnCallTestFunc2(num: String(num))
        return num;
    }

}
