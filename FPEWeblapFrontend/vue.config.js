  const { defineConfig } = require("@vue/cli-service");
  module.exports = defineConfig({
    transpileDependencies: true,
    devServer: {
      port: 4200
    },
    chainWebpack: (config) => {
      // Add the options object to transformAssetUrls
      config.module
        .rule("vue")
        .use("vue-loader")
        .loader("vue-loader")
        .tap((options) => {
          options.transformAssetUrls = {
            video: ["src", "poster"],
            source: "src",
            img: "src",
            image: "xlink:href",
            'b-avatar': 'src',
            'b-img': 'src',
            'b-img-lazy': ['src', 'blank-src'],
            'b-card': 'img-src',
            'b-card-img': 'src',
            'b-card-img-lazy': ['src', 'blank-src'],
            'b-carousel-slide': 'img-src',
            'b-embed': 'src'
          };
          return options;
        });
    }
  });
