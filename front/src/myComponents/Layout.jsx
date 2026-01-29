import React from 'react'
import {useState} from 'react';
import {Header} from './Header.jsx'
import {AdditionModal} from './AdditionModal.jsx'

export const Layout = ({children}) => {
    const [isAddModal, setIsAddModal] = useState(false);

    const openAddModal = () => {
        setIsAddModal(true)
    }
    const closeModal = () => {
        setIsAddModal(false)
    }

    return <React.Fragment>
        <div className='container'>
            <Header openAddModal={openAddModal} />
            {children}
            {isAddModal && <AdditionModal closeModal={closeModal}/>}
        </div>
    </React.Fragment>
}