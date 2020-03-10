config.resolve.modules.push("processedResources/Js/main");

config.module.rules.push({
    test: /\.css$/,
    use: [
        "css-loader" // translates CSS into CommonJS
    ]
});
