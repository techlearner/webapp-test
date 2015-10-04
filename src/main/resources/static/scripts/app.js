'use strict';

/**
 * @ngdoc home
 * @name yapp
 * @description
 * # yapp
 *
 * Main module of the application.
 */
angular
    .module('yapp', [
        'ui.router',
        'ngAnimate',
        'ui.bootstrap',
        'textAngular',
        'ui.calendar',
        'perfect_scrollbar',
        'angular-loading-bar',
        'chart.js',
        'angular-growl',
        'gridshore.c3js.chart',
        'growlNotifications',
        'yappay',
        'yapServices'
    ])
    .config(['cfpLoadingBarProvider', function (cfpLoadingBarProvider) {
        cfpLoadingBarProvider.latencyThreshold = 5;
        cfpLoadingBarProvider.includeSpinner = false;
    }])
    .config(function ($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.when('/dashboard', '/dashboard/userupload');
        $urlRouterProvider.otherwise('/dashboard');

        $stateProvider
            .state('plain', {
                abstract: true,
                url: '',
                templateUrl: 'views/layouts/plain.html'
            })
            .state('boxed', {
                abstract: true,
                url: '',
                parent: 'plain',
                templateUrl: 'views/layouts/boxed.html'
            })

            .state('login', {
                url: '/login',
                parent: 'boxed',
                templateUrl: 'views/pages/login.html',
                controller: 'LoginCtrl'
            })
            .state('dashboard', {
                url: '/dashboard',
                parent: 'plain',
                templateUrl: 'views/layouts/dashboard.html',
                controller: 'DashboardCtrl'
            })
            .state('home', {
                url: '/home',
                parent: 'dashboard',
                templateUrl: 'views/pages/dashboard/home.html'
            })
            .state('reports', {
                url: '/reports',
                parent: 'dashboard',
                templateUrl: 'views/pages/dashboard/reports.html'
            })
            .state('accordion', {
                url: '/ui-elements/accordion',
                parent: 'dashboard',
                templateUrl: 'views/pages/dashboard/ui-elements/accordion.html',
                controller: 'AccordionDemoCtrl'
            })
            .state('alert', {
                url: '/ui-elements/alert',
                parent: 'dashboard',
                templateUrl: 'views/pages/dashboard/ui-elements/alert.html',
                controller: 'AlertDemoCtrl'
            })
            .state('collapse', {
                url: '/ui-elements/collapse',
                parent: 'dashboard',
                templateUrl: 'views/pages/dashboard/ui-elements/collapse.html',
                controller: 'CollapseDemoCtrl'
            })
            .state('datepicker', {
                url: '/ui-elements/datepicker',
                parent: 'dashboard',
                templateUrl: 'views/pages/dashboard/ui-elements/datepicker.html',
                controller: 'DatepickerDemoCtrl'
            })
            .state('dropdown', {
                url: '/ui-elements/dropdown',
                parent: 'dashboard',
                templateUrl: 'views/pages/dashboard/ui-elements/dropdown.html',
                controller: 'DropdownCtrl'
            })
            .state('other-elements', {
                url: '/ui-elements/other-elements',
                parent: 'dashboard',
                templateUrl: 'views/pages/dashboard/ui-elements/other-elements.html'
            })
            .state('modal', {
                url: '/ui-elements/modal',
                parent: 'dashboard',
                templateUrl: 'views/pages/dashboard/ui-elements/modal.html',
                controller: 'ModalDemoCtrl'
            })
            .state('pagination', {
                url: '/ui-elements/pagination',
                parent: 'dashboard',
                templateUrl: 'views/pages/dashboard/ui-elements/pagination.html',
                controller: 'PaginationDemoCtrl'
            })
            .state('popover', {
                url: '/ui-elements/popover',
                parent: 'dashboard',
                templateUrl: 'views/pages/dashboard/ui-elements/popover.html',
                controller: 'PopoverDemoCtrl'
            })
            .state('progressbars', {
                url: '/ui-elements/progressbars',
                parent: 'dashboard',
                templateUrl: 'views/pages/dashboard/ui-elements/progressbar.html',
                controller: 'ProgressDemoCtrl'
            })
            .state('tabs', {
                url: '/ui-elements/tabs',
                parent: 'dashboard',
                templateUrl: 'views/pages/dashboard/ui-elements/tabs.html',
                controller: 'TabsDemoCtrl'
            })
            .state('register', {
                url: '/register',
                parent: 'dashboard',
                templateUrl: 'views/pages/dashboard/register.html'
            })
            .state('amountload', {
                url: '/amountload',
                parent: 'dashboard',
                templateUrl: 'views/pages/dashboard/business/amountload.html'
            })
            .state('getbalance', {
                url: '/getbalance',
                parent: 'dashboard',
                templateUrl: 'views/pages/dashboard/business/getbalance.html'
            })
            .state('profile', {
                url: '/profile',
                parent: 'dashboard',
                templateUrl: 'views/pages/dashboard/profile.html'
            })
            .state('amountrefund', {
                url: '/amountrefund',
                parent: 'dashboard',
                templateUrl: 'views/pages/dashboard/business/amountrefund.html'
            })
            .state('elements', {
                url: '/form/elements',
                parent: 'dashboard',
                templateUrl: 'views/pages/dashboard/forms/elements.html'
            })
            .state('components', {
                url: '/form/components',
                parent: 'dashboard',
                templateUrl: 'views/pages/dashboard/forms/components.html'
            })
            .state('invoice', {
                url: '/invoice',
                parent: 'dashboard',
                templateUrl: 'views/pages/dashboard/invoice.html'
            })
            .state('inbox', {
                url: '/mail/inbox',
                parent: 'dashboard',
                templateUrl: 'views/pages/dashboard/mail/inbox.html'
            })
            .state('typography', {
                url: '/ui-elements/typography',
                parent: 'dashboard',
                templateUrl: 'views/pages/dashboard/ui-elements/typography.html'
            })
            .state('icons', {
                url: '/ui-elements/icons',
                parent: 'dashboard',
                templateUrl: 'views/pages/dashboard/ui-elements/icons.html'
            })
            .state('compose', {
                url: '/mail/compose',
                parent: 'dashboard',
                templateUrl: 'views/pages/dashboard/mail/compose.html'
            })
            .state('blank', {
                url: '/blank',
                parent: 'dashboard',
                templateUrl: 'views/pages/dashboard/blank.html'

            })
            .state('calendar', {
                url: '/calendar',
                parent: 'dashboard',
                templateUrl: 'views/pages/dashboard/calendar.html'

            })
            .state('signup', {
                url: '/signup',
                templateUrl: 'views/pages/signup.html',
                parent: 'boxed',
                controller: 'LoginCtrl'
            })
            .state('forgot-password', {
                url: '/forgot-password',
                parent: 'boxed',
                templateUrl: 'views/pages/forgot-password.html',
                controller: 'LoginCtrl'
            })
            .state('404-page', {
                url: '/404-page',
                parent: 'boxed',
                templateUrl: 'views/pages/404-page.html',
                controller: 'LoginCtrl'
            })
            .state('timepicker', {
                url: '/ui-elements/timepicker',
                parent: 'dashboard',
                templateUrl: 'views/pages/dashboard/ui-elements/timepicker.html',
                controller: 'TimepickerDemoCtrl'
            })
            .state('button', {
                url: '/ui-elements/button',
                parent: 'dashboard',
                templateUrl: 'views/pages/dashboard/ui-elements/button.html',
                controller: 'ButtonsCtrl'
            })
            .state('c3chart', {
                url: '/charts/c3chart',
                parent: 'dashboard',
                templateUrl: 'views/pages/dashboard/charts/c3chart.html'
            })
            .state('chartjs', {
                url: '/charts/chartjs',
                parent: 'dashboard',
                templateUrl: 'views/pages/dashboard/charts/chartjs.html',
                controller: 'ChartCtrl'
            })
            .state('userupload', {
                url: '/userupload',
                parent: 'dashboard',
                templateUrl: 'views/pages/dashboard/business/userupload.html',
                controller: 'UserUploadCtrl'
            })
            .state('approveAmountLoad', {
            	url: "/approveamountload",
            	parent: 'dashboard',
            	templateUrl : 'views/pages/dashboard/business/approveamountload.html'
            	
            })
             .state('amountloadUpload', {
            	url: "/amountloadUpload",
            	parent: 'dashboard',
            	templateUrl : 'views/pages/dashboard/business/amountloadUpload.html'
            	
            })
            .state('checkerDashboard', {
            	url: '/checkerDashboard',
            	parent: 'dashboard',
            	templateUrl: 'views/pages/dashboard/business/checkerDashboard.html'
            });
    });

	
