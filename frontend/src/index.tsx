import React from 'react';
import ReactDOM from 'react-dom/client';
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import './index.css';
import reportWebVitals from './reportWebVitals';
import Landing from './pages/Landing';
import Error from "./pages/Error";
import Report from './pages/Report';
import { AnimatePresence } from "framer-motion";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Landing />,
    errorElement: <Error />,
  },
  {
    path: "Report",
    element: <Report />,
    errorElement: <Error />,
  },
]);

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
root.render(
  <React.StrictMode>
      <AnimatePresence>
        <RouterProvider router={router} />
      </AnimatePresence>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
