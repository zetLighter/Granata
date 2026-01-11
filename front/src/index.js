import * as ReactDOMClient from 'react-dom/client';
import {BrowserRouter} from 'react-router-dom'

import MyApp from './MyApp.js'
import './css/myStyle.css';

const app = ReactDOMClient.createRoot(document.getElementById('root'))

app.render(<BrowserRouter><MyApp/></BrowserRouter>)