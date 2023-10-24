/** @jsxImportSource @emotion/react */
import { useState } from "react";
import { topDivStyle, brandDivStyle } from "./SideBar.style";
import SideBarItem from "./SideBarItem";
import { Item } from "../../../type";

export default function SideBar() {

    const [menuItems, setMenuItems] = useState<Item[]>([
        {id: 1, title: '프로모션 관리', selected: true},
        {id: 2, title: '쿠폰 관리', selected: false},
        {id: 3, title: '통계', selected: false},
    ]);

    const onItemClick = (itemId: number) => {
        const updateMenuItem = menuItems.map((item) => {
            if (item.id === itemId) {
                return {...item, selected: true};
            } else {
                return {...item, selected: false};
            }
        });
        setMenuItems(updateMenuItem);
    };

    return (
        <div css={topDivStyle}>
            <div css={brandDivStyle}>
                우아한 프로모션
            </div>
            <div>
                {menuItems.map((item) => (
                    <SideBarItem key={item.id} item={item} onItemClick={onItemClick} />
                ))}
            </div>
        </div>
    );
}
