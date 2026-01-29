import {useState} from 'react';
import {Routes, Route} from 'react-router-dom' 
import {Layout} from './myComponents/Layout.jsx'
import {Catalog} from './pages/Catalog'
import {Orders} from './pages/Orders.jsx'
import {Auth} from './pages/Auth.jsx'
import {OrderModal} from './myComponents/OrderModal.jsx'
import {EmployeePanel} from './pages/EmployeePanel.jsx'

function MyApp () {
    const [isOrderModal, setIsOrderModal] = useState(false);
    const [productId, setProductId] = useState(null);
    const openOrderModal = (id) => {
        setProductId(id)
        setIsOrderModal(true)
    }
    const closeOrderModal = () => {
        setIsOrderModal(false)
    }
    return (
        <Layout>
            <Routes>
                <Route path='/' element={<Catalog openOrderModal={openOrderModal}/>} />
                <Route path='/orders' element={<Orders/>}/>
                <Route path='/auth' element={<Auth/>} />
                <Route path='/panel' element={<EmployeePanel/>}/>
            </Routes>
            {isOrderModal && <OrderModal productId={productId} closeOrderModal={closeOrderModal} />}
        </Layout>
    )
}

export default MyApp;