//
//  AppDelegate.swift
//  SampleIOSApp
//
//  Created by Oleg Lipskiy on 21.06.2020.
//  Copyright © 2020 Oleg Lipskiy. All rights reserved.
//

import UIKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?

    func application(_ application: UIApplication,
                     didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        let rootViewController = createRootViewController()

        setupWindow(forRoot: rootViewController)
        return true
    }
    

    private func setupWindow(forRoot viewController: UIViewController) {
        window = UIWindow(frame: UIScreen.main.bounds)
        window!.backgroundColor = .white
        window!.rootViewController = viewController
        window!.makeKeyAndVisible()
    }

    private func createRootViewController() -> UIViewController {
        return ViewController()
    }

}

