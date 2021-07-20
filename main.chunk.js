(this["webpackJsonppdf-from-images-react-app"] = this["webpackJsonppdf-from-images-react-app"] || []).push([["main"], {

/***/ "./node_modules/css-loader/dist/cjs.js?!./node_modules/postcss-loader/src/index.js?!./src/App.css":
/*!************************************************************************************************************************!*\
  !*** ./node_modules/css-loader/dist/cjs.js??ref--5-oneOf-4-1!./node_modules/postcss-loader/src??postcss!./src/App.css ***!
  \************************************************************************************************************************/
/*! exports provided: default */
/***/ (function (module, __webpack_exports__, __webpack_require__) {

      "use strict";
      __webpack_require__.r(__webpack_exports__);
/* harmony import */ var _node_modules_css_loader_dist_runtime_api_js__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../node_modules/css-loader/dist/runtime/api.js */ "./node_modules/css-loader/dist/runtime/api.js");
/* harmony import */ var _node_modules_css_loader_dist_runtime_api_js__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_node_modules_css_loader_dist_runtime_api_js__WEBPACK_IMPORTED_MODULE_0__);
      // Imports

      var ___CSS_LOADER_EXPORT___ = _node_modules_css_loader_dist_runtime_api_js__WEBPACK_IMPORTED_MODULE_0___default()(true);
      // Module
      ___CSS_LOADER_EXPORT___.push([module.i, "body {\n  margin: 20px;\n}\n\n.images-container {\n    display: flex;\n    box-sizing: border-box;\n    width: 100%;\n    height: 500px;\n    max-height: 30vh;\n    padding: 16px 20px;\n    overflow: auto;\n    background: #efefef;\n    border-radius: 6px;\n    box-shadow: 0 3px 6px rgba(0,0,0,0.16), 0 3px 6px rgba(0,0,0,0.23);\n}\n\n.images-container:after {\n  content: '';\n  padding-right: 20px;\n}\n\n.uploaded-image {\n  height: 100%;\n  width: auto;\n  box-shadow: 0 3px 6px rgba(0,0,0,0.26), 0 3px 6px rgba(0,0,0,0.3);\n}\n\n.uploaded-image:not(:first-child) {\n  margin-left: 20px;\n}\n\n.buttons-container {\n  display: flex;\n  align-items: center;\n  justify-content: flex-end;\n  margin-top: 20px;\n}\n\n.button {\n  display: inline-block;\n  background-color: #4CAF50;\n  color: white;\n  font-size: 18px;\n  font-weight: 600;\n  text-align: center;\n  text-decoration: none;\n  padding: 16px 32px;\n  border: none;\n  border-radius: 3px;\n  box-sizing: border-box;\n  box-shadow: 0 3px 6px rgba(0,0,0,0.3)\n}\n\n.button:focus {\n  outline: none;\n}\n\n.button:active {\n  filter: brightness(0.9);\n}\n\n.button:disabled {\n  background-color: #ababab;\n}\n\n.button:not(:disabled) {\n  cursor: pointer;\n}\n\n.button:not(:first-child) {\n  margin-left: 16px;\n}", "", { "version": 3, "sources": ["webpack://src/App.css"], "names": [], "mappings": "AAAA;EACE,YAAY;AACd;;AAEA;IACI,aAAa;IACb,sBAAsB;IACtB,WAAW;IACX,aAAa;IACb,gBAAgB;IAChB,kBAAkB;IAClB,cAAc;IACd,mBAAmB;IACnB,kBAAkB;IAClB,kEAAkE;AACtE;;AAEA;EACE,WAAW;EACX,mBAAmB;AACrB;;AAEA;EACE,YAAY;EACZ,WAAW;EACX,iEAAiE;AACnE;;AAEA;EACE,iBAAiB;AACnB;;AAEA;EACE,aAAa;EACb,mBAAmB;EACnB,yBAAyB;EACzB,gBAAgB;AAClB;;AAEA;EACE,qBAAqB;EACrB,yBAAyB;EACzB,YAAY;EACZ,eAAe;EACf,gBAAgB;EAChB,kBAAkB;EAClB,qBAAqB;EACrB,kBAAkB;EAClB,YAAY;EACZ,kBAAkB;EAClB,sBAAsB;EACtB;AACF;;AAEA;EACE,aAAa;AACf;;AAEA;EACE,uBAAuB;AACzB;;AAEA;EACE,yBAAyB;AAC3B;;AAEA;EACE,eAAe;AACjB;;AAEA;EACE,iBAAiB;AACnB", "sourcesContent": ["body {\n  margin: 20px;\n}\n\n.images-container {\n    display: flex;\n    box-sizing: border-box;\n    width: 100%;\n    height: 500px;\n    max-height: 30vh;\n    padding: 16px 20px;\n    overflow: auto;\n    background: #efefef;\n    border-radius: 6px;\n    box-shadow: 0 3px 6px rgba(0,0,0,0.16), 0 3px 6px rgba(0,0,0,0.23);\n}\n\n.images-container:after {\n  content: '';\n  padding-right: 20px;\n}\n\n.uploaded-image {\n  height: 100%;\n  width: auto;\n  box-shadow: 0 3px 6px rgba(0,0,0,0.26), 0 3px 6px rgba(0,0,0,0.3);\n}\n\n.uploaded-image:not(:first-child) {\n  margin-left: 20px;\n}\n\n.buttons-container {\n  display: flex;\n  align-items: center;\n  justify-content: flex-end;\n  margin-top: 20px;\n}\n\n.button {\n  display: inline-block;\n  background-color: #4CAF50;\n  color: white;\n  font-size: 18px;\n  font-weight: 600;\n  text-align: center;\n  text-decoration: none;\n  padding: 16px 32px;\n  border: none;\n  border-radius: 3px;\n  box-sizing: border-box;\n  box-shadow: 0 3px 6px rgba(0,0,0,0.3)\n}\n\n.button:focus {\n  outline: none;\n}\n\n.button:active {\n  filter: brightness(0.9);\n}\n\n.button:disabled {\n  background-color: #ababab;\n}\n\n.button:not(:disabled) {\n  cursor: pointer;\n}\n\n.button:not(:first-child) {\n  margin-left: 16px;\n}"], "sourceRoot": "" }]);
// Exports
/* harmony default export */ __webpack_exports__["default"] = (___CSS_LOADER_EXPORT___);


      /***/
}),

/***/ "./node_modules/css-loader/dist/cjs.js?!./node_modules/postcss-loader/src/index.js?!./src/index.css":
/*!**************************************************************************************************************************!*\
  !*** ./node_modules/css-loader/dist/cjs.js??ref--5-oneOf-4-1!./node_modules/postcss-loader/src??postcss!./src/index.css ***!
  \**************************************************************************************************************************/
/*! exports provided: default */
/***/ (function (module, __webpack_exports__, __webpack_require__) {

      "use strict";
      __webpack_require__.r(__webpack_exports__);
/* harmony import */ var _node_modules_css_loader_dist_runtime_api_js__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../node_modules/css-loader/dist/runtime/api.js */ "./node_modules/css-loader/dist/runtime/api.js");
/* harmony import */ var _node_modules_css_loader_dist_runtime_api_js__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_node_modules_css_loader_dist_runtime_api_js__WEBPACK_IMPORTED_MODULE_0__);
      // Imports

      var ___CSS_LOADER_EXPORT___ = _node_modules_css_loader_dist_runtime_api_js__WEBPACK_IMPORTED_MODULE_0___default()(true);
      // Module
      ___CSS_LOADER_EXPORT___.push([module.i, "body {\n  margin: 0;\n  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen',\n    'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue',\n    sans-serif;\n  -webkit-font-smoothing: antialiased;\n  -moz-osx-font-smoothing: grayscale;\n}\n\ncode {\n  font-family: source-code-pro, Menlo, Monaco, Consolas, 'Courier New',\n    monospace;\n}\n", "", { "version": 3, "sources": ["webpack://src/index.css"], "names": [], "mappings": "AAAA;EACE,SAAS;EACT;;cAEY;EACZ,mCAAmC;EACnC,kCAAkC;AACpC;;AAEA;EACE;aACW;AACb", "sourcesContent": ["body {\n  margin: 0;\n  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen',\n    'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue',\n    sans-serif;\n  -webkit-font-smoothing: antialiased;\n  -moz-osx-font-smoothing: grayscale;\n}\n\ncode {\n  font-family: source-code-pro, Menlo, Monaco, Consolas, 'Courier New',\n    monospace;\n}\n"], "sourceRoot": "" }]);
// Exports
/* harmony default export */ __webpack_exports__["default"] = (___CSS_LOADER_EXPORT___);


      /***/
}),

/***/ "./src/App.css":
/*!*********************!*\
  !*** ./src/App.css ***!
  \*********************/
/*! no static exports found */
/***/ (function (module, exports, __webpack_require__) {

      var api = __webpack_require__(/*! ../node_modules/style-loader/dist/runtime/injectStylesIntoStyleTag.js */ "./node_modules/style-loader/dist/runtime/injectStylesIntoStyleTag.js");
      var content = __webpack_require__(/*! !../node_modules/css-loader/dist/cjs.js??ref--5-oneOf-4-1!../node_modules/postcss-loader/src??postcss!./App.css */ "./node_modules/css-loader/dist/cjs.js?!./node_modules/postcss-loader/src/index.js?!./src/App.css");

      content = content.__esModule ? content.default : content;

      if (typeof content === 'string') {
        content = [[module.i, content, '']];
      }

      var options = {};

      options.insert = "head";
      options.singleton = false;

      var update = api(content, options);


      if (true) {
        if (!content.locals || module.hot.invalidate) {
          var isEqualLocals = function isEqualLocals(a, b, isNamedExport) {
            if (!a && b || a && !b) {
              return false;
            }

            var p;

            for (p in a) {
              if (isNamedExport && p === 'default') {
                // eslint-disable-next-line no-continue
                continue;
              }

              if (a[p] !== b[p]) {
                return false;
              }
            }

            for (p in b) {
              if (isNamedExport && p === 'default') {
                // eslint-disable-next-line no-continue
                continue;
              }

              if (!a[p]) {
                return false;
              }
            }

            return true;
          };
          var oldLocals = content.locals;

          module.hot.accept(
      /*! !../node_modules/css-loader/dist/cjs.js??ref--5-oneOf-4-1!../node_modules/postcss-loader/src??postcss!./App.css */ "./node_modules/css-loader/dist/cjs.js?!./node_modules/postcss-loader/src/index.js?!./src/App.css",
            function () {
              content = __webpack_require__(/*! !../node_modules/css-loader/dist/cjs.js??ref--5-oneOf-4-1!../node_modules/postcss-loader/src??postcss!./App.css */ "./node_modules/css-loader/dist/cjs.js?!./node_modules/postcss-loader/src/index.js?!./src/App.css");

              content = content.__esModule ? content.default : content;

              if (typeof content === 'string') {
                content = [[module.i, content, '']];
              }

              if (!isEqualLocals(oldLocals, content.locals)) {
                module.hot.invalidate();

                return;
              }

              oldLocals = content.locals;

              update(content);
            }
          )
        }

        module.hot.dispose(function () {
          update();
        });
      }

      module.exports = content.locals || {};

      /***/
}),

/***/ "./src/App.tsx":
/*!*********************!*\
  !*** ./src/App.tsx ***!
  \*********************/
/*! exports provided: default */
/***/ (function (module, __webpack_exports__, __webpack_require__) {

      "use strict";
      __webpack_require__.r(__webpack_exports__);
/* WEBPACK VAR INJECTION */(function (__react_refresh_utils__, __react_refresh_error_overlay__) {/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! react */ "./node_modules/react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var jspdf__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! jspdf */ "./node_modules/jspdf/dist/jspdf.es.min.js");
/* harmony import */ var _App_css__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./App.css */ "./src/App.css");
/* harmony import */ var _App_css__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(_App_css__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! react/jsx-dev-runtime */ "./node_modules/react/jsx-dev-runtime.js");
/* harmony import */ var react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_3___default = /*#__PURE__*/__webpack_require__.n(react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_3__);
        __webpack_require__.$Refresh$.runtime = __webpack_require__(/*! ./node_modules/react-refresh/runtime.js */ "./node_modules/react-refresh/runtime.js");
        __webpack_require__.$Refresh$.setup(module.i);

        var _jsxFileName = "/home/andythebreaker/progress/n9/camPdfHttpAndriod/pdf-from-images-react-app/src/App.tsx",
          _s = __webpack_require__.$Refresh$.signature();



        // New class with additional fields for Image




        class CustomImage extends Image {
          constructor(mimeType) {
            super();
            this.mimeType = mimeType;
          } // `imageType` is a required input for generating a PDF for an image.


          get imageType() {
            return this.mimeType.split("/")[1];
          }

        } // Each image is loaded and an object URL is created.


        const fileToImageURL = file => {
          return new Promise((resolve, reject) => {
            const image = new CustomImage(file.type);

            image.onload = () => {
              resolve(image);
            };

            image.onerror = () => {
              reject(new Error("Failed to convert File to Image"));
            };

            console.log(image);
            var image_src_URL_createObjectURL_file = URL.createObjectURL(file);
            console.log(image_src_URL_createObjectURL_file);

            image.src = image_src_URL_createObjectURL_file;
          });
        }; // The dimensions are in millimeters.


        const A4_PAPER_DIMENSIONS = {
          width: 210,
          height: 297
        };
        const A4_PAPER_RATIO = A4_PAPER_DIMENSIONS.width / A4_PAPER_DIMENSIONS.height;

        // Calculates the best possible position of an image on the A4 paper format,
        // so that the maximal area of A4 is used and the image ratio is preserved.
        const imageDimensionsOnA4 = dimensions => {
          const isLandscapeImage = dimensions.width >= dimensions.height; // If the image is in landscape, the full width of A4 is used.

          if (isLandscapeImage) {
            return {
              width: A4_PAPER_DIMENSIONS.width,
              height: A4_PAPER_DIMENSIONS.width / (dimensions.width / dimensions.height)
            };
          } // If the image is in portrait and the full height of A4 would skew
          // the image ratio, we scale the image dimensions.


          const imageRatio = dimensions.width / dimensions.height;

          if (imageRatio > A4_PAPER_RATIO) {
            const imageScaleFactor = A4_PAPER_RATIO * dimensions.height / dimensions.width;
            const scaledImageHeight = A4_PAPER_DIMENSIONS.height * imageScaleFactor;
            return {
              height: scaledImageHeight,
              width: scaledImageHeight * imageRatio
            };
          } // The full height of A4 can be used without skewing the image ratio.


          return {
            width: A4_PAPER_DIMENSIONS.height / (dimensions.height / dimensions.width),
            height: A4_PAPER_DIMENSIONS.height
          };
        }; // Creates a PDF document containing all the uploaded images.


        function dataURItoBlob_copy(dataURI) {
          var byteString = atob(dataURI.split(',')[1]);
          var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];
          var ab = new ArrayBuffer(byteString.length);
          var ia = new Uint8Array(ab);
          for (var i = 0; i < byteString.length; i++) {
            ia[i] = byteString.charCodeAt(i);
          }
          return new Blob([ab], { type: mimeString });
        }

        const generatePdfFromImages = images => {
          // Default export is A4 paper, portrait, using millimeters for units.
          const doc = new jspdf__WEBPACK_IMPORTED_MODULE_1__["default"](); // We let the images add all pages,
          // therefore the first default page can be removed.

          doc.deletePage(1);
          images.forEach(image => {
            const imageDimensions = imageDimensionsOnA4({
              width: image.width,
              height: image.height
            });
            doc.addPage();
            console.log(image.src);
            console.log("success");
            var my_blob = dataURItoBlob_copy("data:image/jpeg;base64,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");
            var my_success = URL.createObjectURL(my_blob);
            doc.addImage(/*image.src*/my_success, image.imageType, // Images are vertically and horizontally centered on the page.
              (A4_PAPER_DIMENSIONS.width - imageDimensions.width) / 2, (A4_PAPER_DIMENSIONS.height - imageDimensions.height) / 2, imageDimensions.width, imageDimensions.height);
          }); // Creates a PDF and opens it in a new browser tab.

          const pdfURL = doc.output("bloburl");
          window.open(pdfURL, "_blank");
        };

        function App() {
          _s();

          // State for uploaded images
          const [uploadedImages, setUploadedImages] = react__WEBPACK_IMPORTED_MODULE_0___default.a.useState([]);
          const handleImageUpload = react__WEBPACK_IMPORTED_MODULE_0___default.a.useCallback(event => {
            // `event.target.files` is of type `FileList`,
            // we convert it to Array for easier manipulation.
            const fileList = event.target.files;
            console.log(event.target);
            const fileArray = fileList ? Array.from(fileList) : []; // Uploaded images are read and the app state is updated.
            console.log(fileArray);
            const fileToImagePromises = fileArray.map(fileToImageURL);
            Promise.all(fileToImagePromises).then(setUploadedImages);
          }, [setUploadedImages]);
          const cleanUpUploadedImages = react__WEBPACK_IMPORTED_MODULE_0___default.a.useCallback(() => {
            setUploadedImages([]);
            uploadedImages.forEach(image => {
              // The URL.revokeObjectURL() releases an existing object URL
              // which was previously created by URL.createObjectURL().
              // It lets the browser know not to keep the reference to the file any longer.
              console.log(image.src);
              URL.revokeObjectURL(image.src);
            });
          }, [setUploadedImages, uploadedImages]);
          const handleGeneratePdfFromImages = react__WEBPACK_IMPORTED_MODULE_0___default.a.useCallback(() => {
            generatePdfFromImages(uploadedImages);
            cleanUpUploadedImages();
          }, [uploadedImages, cleanUpUploadedImages]);
          return /*#__PURE__*/Object(react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_3__["jsxDEV"])(react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_3__["Fragment"], {
            children: [/*#__PURE__*/Object(react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_3__["jsxDEV"])("h1", {
              children: "Convert images to PDFs"
            }, void 0, false, {
              fileName: _jsxFileName,
              lineNumber: 153,
              columnNumber: 7
            }, this), /*#__PURE__*/Object(react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_3__["jsxDEV"])("div", {
              className: "images-container",
              children: uploadedImages.length > 0 ? uploadedImages.map(image => /*#__PURE__*/Object(react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_3__["jsxDEV"])("img", {
                src: image.src,
                className: "uploaded-image"
              }, image.src, false, {
                fileName: _jsxFileName,
                lineNumber: 159,
                columnNumber: 13
              }, this)) : /*#__PURE__*/Object(react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_3__["jsxDEV"])("p", {
                children: "Upload some images..."
              }, void 0, false, {
                fileName: _jsxFileName,
                lineNumber: 162,
                columnNumber: 11
              }, this)
            }, void 0, false, {
              fileName: _jsxFileName,
              lineNumber: 156,
              columnNumber: 7
            }, this), /*#__PURE__*/Object(react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_3__["jsxDEV"])("div", {
              className: "buttons-container",
              children: [/*#__PURE__*/Object(react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_3__["jsxDEV"])("label", {
                htmlFor: "file-input",
                children: [/*#__PURE__*/Object(react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_3__["jsxDEV"])("span", {
                  className: "button",
                  children: "Upload images"
                }, void 0, false, {
                  fileName: _jsxFileName,
                  lineNumber: 170,
                  columnNumber: 11
                }, this), /*#__PURE__*/Object(react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_3__["jsxDEV"])("input", {
                  id: "file-input",
                  type: "file",
                  accept: "image/*",
                  onChange: handleImageUpload // Native file input is hidden only for styling purposes
                  ,
                  style: {
                    display: "none"
                  },
                  multiple: true
                }, void 0, false, {
                  fileName: _jsxFileName,
                  lineNumber: 171,
                  columnNumber: 11
                }, this)]
              }, void 0, true, {
                fileName: _jsxFileName,
                lineNumber: 169,
                columnNumber: 9
              }, this), /*#__PURE__*/Object(react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_3__["jsxDEV"])("button", {
                onClick: handleGeneratePdfFromImages,
                className: "button",
                disabled: uploadedImages.length === 0,
                children: "Generate PDF"
              }, void 0, false, {
                fileName: _jsxFileName,
                lineNumber: 183,
                columnNumber: 9
              }, this)]
            }, void 0, true, {
              fileName: _jsxFileName,
              lineNumber: 167,
              columnNumber: 7
            }, this)]
          }, void 0, true);
        }

        _s(App, "RoJm5bhYjdUXQQ/RkmFtEfbf7FU=");

        _c = App;
/* harmony default export */ __webpack_exports__["default"] = (App);

        var _c;

        __webpack_require__.$Refresh$.register(_c, "App");

        const currentExports = __react_refresh_utils__.getModuleExports(module.i);
        __react_refresh_utils__.registerExportsForReactRefresh(currentExports, module.i);

        if (true) {
          const isHotUpdate = !!module.hot.data;
          const prevExports = isHotUpdate ? module.hot.data.prevExports : null;

          if (__react_refresh_utils__.isReactRefreshBoundary(currentExports)) {
            module.hot.dispose(
              /**
               * A callback to performs a full refresh if React has unrecoverable errors,
               * and also caches the to-be-disposed module.
               * @param {*} data A hot module data object from Webpack HMR.
               * @returns {void}
               */
              function hotDisposeCallback(data) {
                // We have to mutate the data object to get data registered and cached
                data.prevExports = currentExports;
              }
            );
            module.hot.accept(
              /**
               * An error handler to allow self-recovering behaviours.
               * @param {Error} error An error occurred during evaluation of a module.
               * @returns {void}
               */
              function hotErrorHandler(error) {
                if (
                  typeof __react_refresh_error_overlay__ !== 'undefined' &&
                  __react_refresh_error_overlay__
                ) {
                  __react_refresh_error_overlay__.handleRuntimeError(error);
                }

                if (typeof __react_refresh_test__ !== 'undefined' && __react_refresh_test__) {
                  if (window.onHotAcceptError) {
                    window.onHotAcceptError(error.message);
                  }
                }

                __webpack_require__.c[module.i].hot.accept(hotErrorHandler);
              }
            );

            if (isHotUpdate) {
              if (
                __react_refresh_utils__.isReactRefreshBoundary(prevExports) &&
                __react_refresh_utils__.shouldInvalidateReactRefreshBoundary(prevExports, currentExports)
              ) {
                module.hot.invalidate();
              } else {
                __react_refresh_utils__.enqueueUpdate(
                  /**
                   * A function to dismiss the error overlay after performing React refresh.
                   * @returns {void}
                   */
                  function updateCallback() {
                    if (
                      typeof __react_refresh_error_overlay__ !== 'undefined' &&
                      __react_refresh_error_overlay__
                    ) {
                      __react_refresh_error_overlay__.clearRuntimeErrors();
                    }
                  }
                );
              }
            }
          } else {
            if (isHotUpdate && __react_refresh_utils__.isReactRefreshBoundary(prevExports)) {
              module.hot.invalidate();
            }
          }
        }
        /* WEBPACK VAR INJECTION */
}.call(this, __webpack_require__(/*! ./node_modules/@pmmmwh/react-refresh-webpack-plugin/lib/runtime/RefreshUtils.js */ "./node_modules/@pmmmwh/react-refresh-webpack-plugin/lib/runtime/RefreshUtils.js"), __webpack_require__(/*! ./node_modules/react-dev-utils/refreshOverlayInterop.js */ "./node_modules/react-dev-utils/refreshOverlayInterop.js")))

      /***/
}),

/***/ "./src/index.css":
/*!***********************!*\
  !*** ./src/index.css ***!
  \***********************/
/*! no static exports found */
/***/ (function (module, exports, __webpack_require__) {

      var api = __webpack_require__(/*! ../node_modules/style-loader/dist/runtime/injectStylesIntoStyleTag.js */ "./node_modules/style-loader/dist/runtime/injectStylesIntoStyleTag.js");
      var content = __webpack_require__(/*! !../node_modules/css-loader/dist/cjs.js??ref--5-oneOf-4-1!../node_modules/postcss-loader/src??postcss!./index.css */ "./node_modules/css-loader/dist/cjs.js?!./node_modules/postcss-loader/src/index.js?!./src/index.css");

      content = content.__esModule ? content.default : content;

      if (typeof content === 'string') {
        content = [[module.i, content, '']];
      }

      var options = {};

      options.insert = "head";
      options.singleton = false;

      var update = api(content, options);


      if (true) {
        if (!content.locals || module.hot.invalidate) {
          var isEqualLocals = function isEqualLocals(a, b, isNamedExport) {
            if (!a && b || a && !b) {
              return false;
            }

            var p;

            for (p in a) {
              if (isNamedExport && p === 'default') {
                // eslint-disable-next-line no-continue
                continue;
              }

              if (a[p] !== b[p]) {
                return false;
              }
            }

            for (p in b) {
              if (isNamedExport && p === 'default') {
                // eslint-disable-next-line no-continue
                continue;
              }

              if (!a[p]) {
                return false;
              }
            }

            return true;
          };
          var oldLocals = content.locals;

          module.hot.accept(
      /*! !../node_modules/css-loader/dist/cjs.js??ref--5-oneOf-4-1!../node_modules/postcss-loader/src??postcss!./index.css */ "./node_modules/css-loader/dist/cjs.js?!./node_modules/postcss-loader/src/index.js?!./src/index.css",
            function () {
              content = __webpack_require__(/*! !../node_modules/css-loader/dist/cjs.js??ref--5-oneOf-4-1!../node_modules/postcss-loader/src??postcss!./index.css */ "./node_modules/css-loader/dist/cjs.js?!./node_modules/postcss-loader/src/index.js?!./src/index.css");

              content = content.__esModule ? content.default : content;

              if (typeof content === 'string') {
                content = [[module.i, content, '']];
              }

              if (!isEqualLocals(oldLocals, content.locals)) {
                module.hot.invalidate();

                return;
              }

              oldLocals = content.locals;

              update(content);
            }
          )
        }

        module.hot.dispose(function () {
          update();
        });
      }

      module.exports = content.locals || {};

      /***/
}),

/***/ "./src/index.tsx":
/*!***********************!*\
  !*** ./src/index.tsx ***!
  \***********************/
/*! no exports provided */
/***/ (function (module, __webpack_exports__, __webpack_require__) {

      "use strict";
      __webpack_require__.r(__webpack_exports__);
/* WEBPACK VAR INJECTION */(function (__react_refresh_utils__, __react_refresh_error_overlay__) {/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! react */ "./node_modules/react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var react_dom__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! react-dom */ "./node_modules/react-dom/index.js");
/* harmony import */ var react_dom__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(react_dom__WEBPACK_IMPORTED_MODULE_1__);
/* harmony import */ var _index_css__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./index.css */ "./src/index.css");
/* harmony import */ var _index_css__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(_index_css__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var _App__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./App */ "./src/App.tsx");
/* harmony import */ var _reportWebVitals__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./reportWebVitals */ "./src/reportWebVitals.ts");
/* harmony import */ var react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! react/jsx-dev-runtime */ "./node_modules/react/jsx-dev-runtime.js");
/* harmony import */ var react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_5___default = /*#__PURE__*/__webpack_require__.n(react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_5__);
        __webpack_require__.$Refresh$.runtime = __webpack_require__(/*! ./node_modules/react-refresh/runtime.js */ "./node_modules/react-refresh/runtime.js");
        __webpack_require__.$Refresh$.setup(module.i);

        var _jsxFileName = "/home/andythebreaker/progress/n9/camPdfHttpAndriod/pdf-from-images-react-app/src/index.tsx";






        react_dom__WEBPACK_IMPORTED_MODULE_1___default.a.render( /*#__PURE__*/Object(react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_5__["jsxDEV"])(react__WEBPACK_IMPORTED_MODULE_0___default.a.StrictMode, {
          children: /*#__PURE__*/Object(react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_5__["jsxDEV"])(_App__WEBPACK_IMPORTED_MODULE_3__["default"], {}, void 0, false, {
            fileName: _jsxFileName,
            lineNumber: 9,
            columnNumber: 5
          }, undefined)
        }, void 0, false, {
          fileName: _jsxFileName,
          lineNumber: 8,
          columnNumber: 3
        }, undefined), document.getElementById('root')); // If you want to start measuring performance in your app, pass a function
        // to log results (for example: reportWebVitals(console.log))
        // or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals

        Object(_reportWebVitals__WEBPACK_IMPORTED_MODULE_4__["default"])();

        const currentExports = __react_refresh_utils__.getModuleExports(module.i);
        __react_refresh_utils__.registerExportsForReactRefresh(currentExports, module.i);

        if (true) {
          const isHotUpdate = !!module.hot.data;
          const prevExports = isHotUpdate ? module.hot.data.prevExports : null;

          if (__react_refresh_utils__.isReactRefreshBoundary(currentExports)) {
            module.hot.dispose(
              /**
               * A callback to performs a full refresh if React has unrecoverable errors,
               * and also caches the to-be-disposed module.
               * @param {*} data A hot module data object from Webpack HMR.
               * @returns {void}
               */
              function hotDisposeCallback(data) {
                // We have to mutate the data object to get data registered and cached
                data.prevExports = currentExports;
              }
            );
            module.hot.accept(
              /**
               * An error handler to allow self-recovering behaviours.
               * @param {Error} error An error occurred during evaluation of a module.
               * @returns {void}
               */
              function hotErrorHandler(error) {
                if (
                  typeof __react_refresh_error_overlay__ !== 'undefined' &&
                  __react_refresh_error_overlay__
                ) {
                  __react_refresh_error_overlay__.handleRuntimeError(error);
                }

                if (typeof __react_refresh_test__ !== 'undefined' && __react_refresh_test__) {
                  if (window.onHotAcceptError) {
                    window.onHotAcceptError(error.message);
                  }
                }

                __webpack_require__.c[module.i].hot.accept(hotErrorHandler);
              }
            );

            if (isHotUpdate) {
              if (
                __react_refresh_utils__.isReactRefreshBoundary(prevExports) &&
                __react_refresh_utils__.shouldInvalidateReactRefreshBoundary(prevExports, currentExports)
              ) {
                module.hot.invalidate();
              } else {
                __react_refresh_utils__.enqueueUpdate(
                  /**
                   * A function to dismiss the error overlay after performing React refresh.
                   * @returns {void}
                   */
                  function updateCallback() {
                    if (
                      typeof __react_refresh_error_overlay__ !== 'undefined' &&
                      __react_refresh_error_overlay__
                    ) {
                      __react_refresh_error_overlay__.clearRuntimeErrors();
                    }
                  }
                );
              }
            }
          } else {
            if (isHotUpdate && __react_refresh_utils__.isReactRefreshBoundary(prevExports)) {
              module.hot.invalidate();
            }
          }
        }
        /* WEBPACK VAR INJECTION */
}.call(this, __webpack_require__(/*! ./node_modules/@pmmmwh/react-refresh-webpack-plugin/lib/runtime/RefreshUtils.js */ "./node_modules/@pmmmwh/react-refresh-webpack-plugin/lib/runtime/RefreshUtils.js"), __webpack_require__(/*! ./node_modules/react-dev-utils/refreshOverlayInterop.js */ "./node_modules/react-dev-utils/refreshOverlayInterop.js")))

      /***/
}),

/***/ "./src/reportWebVitals.ts":
/*!********************************!*\
  !*** ./src/reportWebVitals.ts ***!
  \********************************/
/*! exports provided: default */
/***/ (function (module, __webpack_exports__, __webpack_require__) {

      "use strict";
      __webpack_require__.r(__webpack_exports__);
/* WEBPACK VAR INJECTION */(function (__react_refresh_utils__, __react_refresh_error_overlay__) {
        __webpack_require__.$Refresh$.runtime = __webpack_require__(/*! ./node_modules/react-refresh/runtime.js */ "./node_modules/react-refresh/runtime.js");
        __webpack_require__.$Refresh$.setup(module.i);

        const reportWebVitals = onPerfEntry => {
          if (onPerfEntry && onPerfEntry instanceof Function) {
            __webpack_require__.e(/*! import() */ 0).then(__webpack_require__.bind(null, /*! web-vitals */ "./node_modules/web-vitals/dist/web-vitals.js")).then(({
              getCLS,
              getFID,
              getFCP,
              getLCP,
              getTTFB
            }) => {
              getCLS(onPerfEntry);
              getFID(onPerfEntry);
              getFCP(onPerfEntry);
              getLCP(onPerfEntry);
              getTTFB(onPerfEntry);
            });
          }
        };

/* harmony default export */ __webpack_exports__["default"] = (reportWebVitals);

        const currentExports = __react_refresh_utils__.getModuleExports(module.i);
        __react_refresh_utils__.registerExportsForReactRefresh(currentExports, module.i);

        if (true) {
          const isHotUpdate = !!module.hot.data;
          const prevExports = isHotUpdate ? module.hot.data.prevExports : null;

          if (__react_refresh_utils__.isReactRefreshBoundary(currentExports)) {
            module.hot.dispose(
              /**
               * A callback to performs a full refresh if React has unrecoverable errors,
               * and also caches the to-be-disposed module.
               * @param {*} data A hot module data object from Webpack HMR.
               * @returns {void}
               */
              function hotDisposeCallback(data) {
                // We have to mutate the data object to get data registered and cached
                data.prevExports = currentExports;
              }
            );
            module.hot.accept(
              /**
               * An error handler to allow self-recovering behaviours.
               * @param {Error} error An error occurred during evaluation of a module.
               * @returns {void}
               */
              function hotErrorHandler(error) {
                if (
                  typeof __react_refresh_error_overlay__ !== 'undefined' &&
                  __react_refresh_error_overlay__
                ) {
                  __react_refresh_error_overlay__.handleRuntimeError(error);
                }

                if (typeof __react_refresh_test__ !== 'undefined' && __react_refresh_test__) {
                  if (window.onHotAcceptError) {
                    window.onHotAcceptError(error.message);
                  }
                }

                __webpack_require__.c[module.i].hot.accept(hotErrorHandler);
              }
            );

            if (isHotUpdate) {
              if (
                __react_refresh_utils__.isReactRefreshBoundary(prevExports) &&
                __react_refresh_utils__.shouldInvalidateReactRefreshBoundary(prevExports, currentExports)
              ) {
                module.hot.invalidate();
              } else {
                __react_refresh_utils__.enqueueUpdate(
                  /**
                   * A function to dismiss the error overlay after performing React refresh.
                   * @returns {void}
                   */
                  function updateCallback() {
                    if (
                      typeof __react_refresh_error_overlay__ !== 'undefined' &&
                      __react_refresh_error_overlay__
                    ) {
                      __react_refresh_error_overlay__.clearRuntimeErrors();
                    }
                  }
                );
              }
            }
          } else {
            if (isHotUpdate && __react_refresh_utils__.isReactRefreshBoundary(prevExports)) {
              module.hot.invalidate();
            }
          }
        }
        /* WEBPACK VAR INJECTION */
}.call(this, __webpack_require__(/*! ./node_modules/@pmmmwh/react-refresh-webpack-plugin/lib/runtime/RefreshUtils.js */ "./node_modules/@pmmmwh/react-refresh-webpack-plugin/lib/runtime/RefreshUtils.js"), __webpack_require__(/*! ./node_modules/react-dev-utils/refreshOverlayInterop.js */ "./node_modules/react-dev-utils/refreshOverlayInterop.js")))

      /***/
}),

/***/ 1:
/*!***********************************************************************************************************************************************************************************************!*\
  !*** multi (webpack)/hot/dev-server.js ./node_modules/@pmmmwh/react-refresh-webpack-plugin/client/ReactRefreshEntry.js ./node_modules/react-dev-utils/webpackHotDevClient.js ./src/index.tsx ***!
  \***********************************************************************************************************************************************************************************************/
/*! no static exports found */
/***/ (function (module, exports, __webpack_require__) {

      __webpack_require__(/*! /home/andythebreaker/progress/n9/camPdfHttpAndriod/pdf-from-images-react-app/node_modules/webpack/hot/dev-server.js */"./node_modules/webpack/hot/dev-server.js");
      __webpack_require__(/*! /home/andythebreaker/progress/n9/camPdfHttpAndriod/pdf-from-images-react-app/node_modules/@pmmmwh/react-refresh-webpack-plugin/client/ReactRefreshEntry.js */"./node_modules/@pmmmwh/react-refresh-webpack-plugin/client/ReactRefreshEntry.js");
      __webpack_require__(/*! /home/andythebreaker/progress/n9/camPdfHttpAndriod/pdf-from-images-react-app/node_modules/react-dev-utils/webpackHotDevClient.js */"./node_modules/react-dev-utils/webpackHotDevClient.js");
      module.exports = __webpack_require__(/*! /home/andythebreaker/progress/n9/camPdfHttpAndriod/pdf-from-images-react-app/src/index.tsx */"./src/index.tsx");


      /***/
})

}, [[1, "runtime-main", "vendors~main"]]]);
//# sourceMappingURL=main.chunk.js.map