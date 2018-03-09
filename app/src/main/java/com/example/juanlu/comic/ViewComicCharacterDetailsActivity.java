package com.example.juanlu.comic;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ViewComicCharacterDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comic_character_details);

        activateToolbarWithBackEnabled();

        Intent intent = getIntent();
        ComicCharacter comicCharacter = (ComicCharacter) intent.getSerializableExtra(COMIC_CHARACTER_TRANSFER);

        TextView comicCharacterTitle = findViewById(R.id.comic_character_title);
        comicCharacterTitle.setText(comicCharacter.getmTitle());

        TextView comicCharacterDescription = findViewById(R.id.comic_character_description);
        comicCharacterDescription.setText(comicCharacter.getDescription());

        ImageView comicCharacterImage = findViewById(R.id.comic_character_image);
        Picasso.with(this).load(comicCharacter.getThumbnail())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(comicCharacterImage);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_comic_character_details, menu);

        return true;
    }
}
