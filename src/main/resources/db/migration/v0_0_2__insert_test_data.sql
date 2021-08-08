insert into login(moderator, email, phone_number, password)
values(true, 'spirit.leaf@gmail.com', 7479392201, crypt('thisisabadpassword', gen_salt('bf')));

do $$
declare
        ret_item_id uuid;
        ret_review_id uuid;
begin
        insert into item(item_name, item_description)
        values('iPad', '2022 Model')
        returning item_id into ret_item_id;

        insert into review(text, rating)
        values('wowie great product', 4)
        returning review_id into ret_review_id;

        insert into item_reviews(item_id, review_id)
        values(ret_item_id, ret_review_id);

        insert into review(text, rating)
        values('this is an okay product', 3)
        returning review_id into ret_review_id;

        insert into item_reviews(item_id, review_id)
        values(ret_item_id, ret_review_id);

        insert into review(text, rating)
        values('great product', 2)
        returning review_id into ret_review_id;

        insert into item_reviews(item_id, review_id)
        values(ret_item_id, ret_review_id);

        insert into review(text, rating)
        values('wowie ', 1)
        returning review_id into ret_review_id;

        insert into item_reviews(item_id, review_id)
        values(ret_item_id, ret_review_id);
end $$;
