import {Routes, Route} from 'react-router-dom' 
import {Layout} from './myComponents/Layout.jsx'
import {Catalog} from './pages/Catalog'
import {Orders} from './pages/Orders.jsx'
import {Auth} from './pages/Auth.jsx'

function MyApp () {
    return (
        <Layout>
            <Routes>
                <Route path='/' element={<Catalog/>} />
                <Route path='/orders' element={<Orders/>}/>
                <Route path='/auth' element={<Auth/>} />
            </Routes>
        </Layout>
    )
}

export default MyApp;