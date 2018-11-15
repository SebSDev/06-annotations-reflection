package de.thro.inf.prg3.a06;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import de.thro.inf.prg3.a06.model.Joke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sebastian Schäffler
 * created at 14.11.2018
 * description:
 */
public class JokeAdapter extends TypeAdapter <Joke>
{
	@Override
	public Joke read (final JsonReader reader) throws IOException
	{
		final Joke joke = new Joke();

		reader.beginObject();

		while(reader.hasNext())
		{
			switch (reader.nextName())
			{
				case "type":
					reader.nextString();
					break;
				case "value":
					reader.beginObject();
					while (reader.hasNext())
					{
						switch (reader.nextName())
						{
							case "id":
								joke.setNumber(reader.nextInt());
								break;
							case "joke":
								joke.setContent(reader.nextString());
								break;
							case "categories":
								reader.beginArray();
								List<String> categories = new ArrayList<>();
								while (reader.hasNext())
								{
									categories.add(reader.nextString());
								}
								String[] cs = categories.toArray(new String[0]);
								joke.setRubrics(cs);
								reader.endArray();
								break;
						}
					}
					reader.endObject();

					break;
			}
		}

		reader.endObject();

		return joke;
	}

	@Override
	public void write(final JsonWriter writer, final Joke inst)
	{

	}
}
