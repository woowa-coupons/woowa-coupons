/** @jsxImportSource @emotion/react */
import { Item } from "../../../type";
import { selectedItemDivStyle, itemIconDivStyle, itemDivStyle, itemTitleDivStyle, unselectedItemDivStyle, itemHighlightDivStyle } from "./SideBar.style";

type Props = {
    item: Item;
    onItemClick: (itemId: number) => void;
}

export default function SideBarItem({item, onItemClick}: Props) {

    const handleItemClick = () => {
        onItemClick(item.id);
    }

    return (
        <div
            css={[
                    itemDivStyle, 
                    item.selected
                    ? selectedItemDivStyle 
                    : unselectedItemDivStyle
                ]}
            onClick={handleItemClick}>
            <div 
                css={item.selected? itemHighlightDivStyle : undefined}
                />
            <div css={itemIconDivStyle}>
                <i>@</i>
            </div>
            <div css={itemTitleDivStyle}>
                {item.title}
            </div>
        </div>
    );
}
