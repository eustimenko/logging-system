'use strict';

module.exports = function (grunt) {
    // Load grunt tasks automatically
    require('load-grunt-tasks')(grunt);

    grunt.initConfig({
        // Path settings
        path: {
            in: {
                js: 'assets/js',
                css: 'assets/css',
                less: 'assets/less',
                img: 'assets/img',
                fonts: 'assets/fonts',
                templates: 'assets/templates'
            },
            out: {
                js: 'dist/public/js',
                css: 'dist/public/css',
                fonts: 'dist/public/fonts',
                img: 'dist/public/img',
                images: 'dist/public/images',
                lang: 'dist/public/languages',
                templates: 'dist/public/templates'
            },
            npm: 'node_modules'
        },

        // Clean out folder
        clean: {
            js: '<%= path.out.js %>',
            css: '<%= path.out.css %>',
            less: '<%= path.out.less %>',
            fonts: '<%= path.out.fonts %>',
            img: '<%= path.out.img %>'
        },

        // Combine JS & CSS files in bundles. Copy Bower's scripts & styles
        concat: {
            js: {
                files: {
                    '<%= path.out.js %>/vendor.js': [
                        '<%= path.npm %>/jquery/dist/jquery.js',
                        '<%= path.npm %>/bootstrap/dist/js/bootstrap.js',
                        '<%= path.npm %>/react/dist/react.js',
                        '<%= path.npm %>/react-dom/dist/react-dom.js'
                    ],
                    '<%= path.out.js %>/app.js': [
                        '<%= path.in.js %>/**/*.js'
                    ]
                }
            },
            css: {
                files: {
                    '<%= path.out.css %>/vendor.css': [
                        '<%= path.npm %>/bootstrap/dist/css/bootstrap.css'
                    ],
                    '<%= path.out.css %>/app.css': [
                        '<%= path.in.css %>/**/*.css'
                    ]
                }
            }
        },

        // Minify JS files
        uglify: {
            js: {
                files: {
                    '<%= path.out.js %>/vendor.js': '<%= path.out.js %>/vendor.js',
                    '<%= path.out.js %>/app.js': '<%= path.out.js %>/app.js'
                }
            }
        },

        // Minify CSS files
        cssmin: {
            css: {
                files: {
                    '<%= path.out.css %>/vendor.css': '<%= path.out.css %>/vendor.css',
                    '<%= path.out.css %>/app.css': '<%= path.out.css %>/app.css'
                }
            }
        },

        // Copy additional files
        copy: {
            fonts: {
                files: [
                    {
                        expand: true,
                        cwd: '<%= path.npm %>/bootstrap/fonts/',
                        src: '*',
                        dest: '<%= path.out.fonts %>/'
                    }]
            },
            css: {
                files: [
                    {
                        expand: true,
                        cwd: '<%= path.npm %>/bootstrap/dist/css/',
                        src: 'bootstrap.css.map',
                        dest: '<%= path.out.css %>/'
                    }
                ]
            }
        },

        watch: {
            src: {
                files: [
                    '<%= path.in.js %>/**/*.js',
                    '<%= path.in.less %>/**/*.less',
                    '<%= path.in.css %>/**/*.css',
                    '<%= path.in.templates %>/**/*.html'],
                tasks: ['profile:local'],
                options: {
                    livereload: true,
                    spawn: false,
                    interrupt: true
                }
            }
        }
    });

    // TASKS
    grunt.loadNpmTasks('grunt-contrib-watch');

    grunt.registerTask('build:js', [
        'clean:js',
        'concat:js'
    ]);

    grunt.registerTask('build:js-min', [
        'build:js',
        'uglify:js'
    ]);

    grunt.registerTask('build:css', [
        'clean:css',
        'concat:css',
        'copy:css'
    ]);

    grunt.registerTask('build:css-min', [
        'build:css',
        'cssmin:css'
    ]);

    grunt.registerTask('build:fonts', [
        'clean:fonts',
        'copy:fonts'
    ]);

    // Final tasks

    grunt.registerTask('profile:local', [
        'build:js',
        'build:css',
        'build:fonts'
    ]);

    grunt.registerTask('dev', [
        'profile:local',
        'watch:src'
    ]);

    grunt.registerTask('profile:staging', [
        'build:js-min',
        'build:css-min',
        'build:fonts'
    ]);

    grunt.registerTask('default', [
        'profile:local'
    ]);
};