module.exports = function (grunt) {
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        clean: {
            folder: ["dist/"]
        },
        concat: {
            options: {
                separator: ';'
            },
            dist: {
                src: ['app/walletApp.js'],
                dest: 'dist/<%= pkg.name %>.js'
            }
        },
        jshint: {
            files: ['<%= concat.dist.dest %>'],
            options: {
            }
        },
        browserify: {
            dist: {
                options: {
                    transform: [
                        ["babelify", {"presets": ["es2015"]},
                            "grunt-browserify-css", {"global": true}
                        ]
                    ]
                },
                files: {
                    // if the source file has an extension of es6 then
                    // we change the name of the source file accordingly.
                    // The result file's extension is always .js
                    "dist/<%= pkg.name %>.es5.js": ["<%= concat.dist.dest %>"]
                }
            }
        },
        uglify: {
            options: {
                banner: '/*! <%= pkg.name %> <%= grunt.template.today("dd-mm-yyyy HH-MM-ss") %> */\n'
            },
            dist: {
                files: {
                    'dist/<%= pkg.name %>.min.js': ['dist/<%= pkg.name %>.es5.js']
                }
            }
        }
    });

    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks("grunt-browserify");

    grunt.registerTask('default', ['clean', 'concat', 'jshint', 'browserify', 'uglify']);

};
