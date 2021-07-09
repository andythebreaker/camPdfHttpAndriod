Basically we got a set of movable converters

But there are many errors

notfind:

```
<link rel="icon" href="/favicon.ico">
<link rel="apple-touch-icon" href="/logo192.png">

```

cli error @ vendors~main.chunk.js row 17377

```
WebSocket connection to 'ws://sockjs-node/' failed: 
./node_modules/react-dev-utils/webpackHotDevClient.js	@	vendors~main.chunk.js:17377
__webpack_require__	@	bundle.js:857
fn	@	bundle.js:151
1	@	main.chunk.js:778
__webpack_require__	@	bundle.js:857
checkDeferredModules	@	bundle.js:46
webpackJsonpCallback	@	bundle.js:33
(anonymous)	@	main.chunk.js:1

```

# But these errors do not affect the function operation

I directly capture the client program and run it locally