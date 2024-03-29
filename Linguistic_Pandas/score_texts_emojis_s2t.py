from __future__ import print_function, division
#import example_helper
import json
import csv
import numpy as np
from deepmoji.sentence_tokenizer import SentenceTokenizer
from deepmoji.model_def import deepmoji_emojis
from deepmoji.global_variables import PRETRAINED_PATH, VOCAB_PATH
from nltk import sent_tokenize

def analyse_text_chunk(text_chunk):
    OUTPUT_PATH = 'test_sentences.csv'
    json_file = 'test_sentences.json'

    TEST_SENTENCES = sent_tokenize(text_chunk)


    def top_elements(array, k):
        ind = np.argpartition(array, -k)[-k:]
        return ind[np.argsort(array[ind])][::-1]


    maxlen = 30
    batch_size = 32

    print('Tokenizing using dictionary from {}'.format(VOCAB_PATH))
    with open(VOCAB_PATH, 'r') as f:
        vocabulary = json.load(f)
    st = SentenceTokenizer(vocabulary, maxlen)
    tokenized, _, _ = st.tokenize_sentences(TEST_SENTENCES)

    print('Loading model from {}.'.format(PRETRAINED_PATH))
    model = deepmoji_emojis(maxlen, PRETRAINED_PATH)
    model.summary()

    print('Running predictions.')
    prob = model.predict(tokenized)

    print('Writing results to {}'.format(OUTPUT_PATH))
    scores = []
    for i, t in enumerate(TEST_SENTENCES):
        t_tokens = tokenized[i]
        t_score = [t]
        t_prob = prob[i]
        ind_top = top_elements(t_prob, 5)
        t_score.append(sum(t_prob[ind_top]))
        t_score.extend(ind_top)
        t_score.extend([t_prob[ind] for ind in ind_top])
        scores.append(t_score)
        #print(t_score)

    with open(OUTPUT_PATH, 'w') as csvfile:
        writer = csv.writer(csvfile, delimiter=',', lineterminator='\n')
        writer.writerow(['Text', 'Top5%',
                         'Emoji_1', 'Emoji_2', 'Emoji_3', 'Emoji_4', 'Emoji_5',
                         'Pct_1', 'Pct_2', 'Pct_3', 'Pct_4', 'Pct_5'])
        for i, row in enumerate(scores):
            try:
                writer.writerow(row)
            except Exception:
                print("Exception at row {}!".format(i))


    csv_rows = []
    with open(OUTPUT_PATH,'r') as csvfile:
        reader = csv.DictReader(csvfile)
        title = reader.fieldnames
        for row in reader:
            csv_rows.extend([{title[i]: row[title[i]] for i in range(len(title))}])

    # Convert csv data into json and write it
    # format = 'pretty'
    # with open(json_file, "w") as f:
    #     if format == "pretty":
    #         f.write(json.dumps(csv_rows, sort_keys=False, indent=4, separators=(',', ': '),
    #                            ensure_ascii=False))
    #     else:
    #         f.write(json.dumps(data))

    return json.dumps(csv_rows, sort_keys=False, indent=4, separators=(',', ': '),
                               ensure_ascii=False)